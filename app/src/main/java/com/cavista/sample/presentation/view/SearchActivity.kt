package com.cavista.sample.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.BaseActivity
import com.cavista.sample.presentation.adapter.SearchItemAdapter
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener
import com.cavista.sample.presentation.utils.Resource
import com.cavista.sample.presentation.utils.ResourceState
import com.cavista.sample.presentation.utils.ViewModelFactory
import com.cavista.sample.presentation.viewmodel.SearchViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    private lateinit var searchItemAdapter: SearchItemAdapter

    lateinit var searchViewModel: SearchViewModel
    @Inject
    lateinit var searchViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_search)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this, searchViewModelFactory)
            .get(SearchViewModel::class.java)
        searchViewModel.searchItemLiveData.observe(this, Observer { handleResponse(it) })
        searchViewModel.getSearchItemList()
    }

    private fun handleResponse(resource: Resource<List<UISearchData>>) {
        resource.let { resourceList ->
            when (resourceList.state) {
                ResourceState.ERROR -> {
                    Toast.makeText(
                        this@SearchActivity,
                        resourceList.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                ResourceState.SUCCESS -> {
                    resourceList.data?.let { itemList ->
                        searchItemAdapter.setItems(itemList)
                    }
                }
                ResourceState.LOADING->{
                    // show loading
                }
            }
        }
    }

    private fun initView() {
        searchItemAdapter = SearchItemAdapter(this)
        searchItemListView.apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            setHasFixedSize(true)
            adapter = searchItemAdapter
        }
        searchItemAdapter.setListener(object : OnRecyclerObjectClickListener<UISearchData> {
            override fun onItemClicked(item: UISearchData, position: Int, operationId: Int) {

            }

            override fun onRowClicked(item: UISearchData, position: Int) {

            }
        })
    }

}
