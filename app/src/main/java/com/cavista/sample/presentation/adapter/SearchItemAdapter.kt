package com.cavista.sample.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.adapter.SearchItemViewHolder
import com.cavista.sample.presentation.customeview.BaseViewHolder
import com.cavista.sample.presentation.customeview.CustomRecyclerViewAdapter
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener

class SearchItemAdapter(context: Context) :
    CustomRecyclerViewAdapter<UISearchData, OnRecyclerObjectClickListener<UISearchData>, BaseViewHolder<UISearchData, OnRecyclerObjectClickListener<UISearchData>>>(
        context
    ) {

    private val SEARCH_TYPE: Int = 0
    private val LOADING_TYPE: Int = 1

    override fun getId(position: Int): String {
        return getItemAt(position).id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UISearchData, OnRecyclerObjectClickListener<UISearchData>> {
        when (viewType) {
            SEARCH_TYPE ->
                return SearchItemViewHolder(
                    itemView = inflate(
                        R.layout.search_item,
                        parent,
                        false
                    )
                )
            LOADING_TYPE -> return SearchItemViewHolder(
                itemView = inflate(
                    R.layout.search_item,
                    parent,
                    false
                )
            )
            else -> {
                return SearchItemViewHolder(
                    itemView = inflate(
                        R.layout.search_item,
                        parent,
                        false
                    )
                )
            }
        }

    }


}
