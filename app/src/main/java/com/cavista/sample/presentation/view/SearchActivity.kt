package com.cavista.sample.presentation.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.domain.request.SearchItemRequest
import com.cavista.sample.navigation.Navigation
import com.cavista.sample.presentation.BaseActivity
import com.cavista.sample.presentation.adapter.SearchItemAdapter
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener
import com.cavista.sample.presentation.utils.*
import com.cavista.sample.presentation.utils.DebounceQueryTextListener
import com.cavista.sample.presentation.viewmodel.SearchViewModel
import com.google.gson.Gson
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
        searchViewModel = ViewModelProviders.of(this, searchViewModelFactory)
            .get(SearchViewModel::class.java)
        searchViewModel.searchItemLiveData.observe(this, Observer { handleResponse(it) })
        searchViewModel.errorMsgLiveData.observe(this, Observer { showNoNetworkError(it) })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                searchItemListView.apply {
                    layoutManager = GridLayoutManager(this@SearchActivity, 3)
                    setHasFixedSize(true)
                    adapter = searchItemAdapter
                }
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                searchItemListView.apply {
                    layoutManager = GridLayoutManager(this@SearchActivity, 2)
                    setHasFixedSize(true)
                    adapter = searchItemAdapter
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
              //Implementation not needed
            }

            override fun onRowClicked(item: UISearchData, position: Int) {
                val bundle = Bundle()
                bundle.putString(AppUtils.item_key, Gson().toJson(item))
                Navigation.navigateScreen(
                    this@SearchActivity,
                    SearchDetailsActivity::class.java,
                    false ,bundle
                )
            }
        })

        searchView.setOnQueryTextListener(
            DebounceQueryTextListener(
                this@SearchActivity.lifecycle
            ) { newText ->
                newText?.let {
                    if (it.isEmpty()) {
                        resetView(getString(R.string.search_hint))
                    } else {
                        searchViewModel.getSearchItemList(SearchItemRequest(it, 1))
                    }
                }
            }
        )
    }

    /**
     * Handle response from view model observer
     */
    private fun handleResponse(resource: Resource<List<UISearchData>>) {
        hideProgress()
        resource.let { resourceList ->
            when (resourceList.state) {

                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    resourceList.data?.let { itemList ->
                        if (itemList.isEmpty()) {
                            resetView(getString(R.string.no_result_msg))
                        } else {
                            hideNoResultMsg()
                            searchItemAdapter.setItems(itemList)
                            hideKeyboard(searchView)
                        }
                    }
                }
                ResourceState.ERROR -> {
                    Toast.makeText(
                        this@SearchActivity,
                        resourceList.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun showProgress() {
        progressbar.visibility = View.VISIBLE
        noSearchText.visibility = View.GONE

    }

    private fun hideProgress() {
        progressbar.visibility = View.GONE
    }

    private fun showNoResultMsg(msg: String) {
        noSearchText.text = msg
        noSearchText.visibility = View.VISIBLE
    }

    private fun hideNoResultMsg() {
        noSearchText.visibility = View.GONE
    }

    private fun resetView(msg: String) {
        showNoResultMsg(msg)
        hideProgress()
        hideKeyboard(searchView)
        searchItemAdapter.setItems(emptyList())
    }

    private fun showNoNetworkError(msg: String) {
        showNoResultMsg(msg)
        hideKeyboard(searchView)
    }
}
