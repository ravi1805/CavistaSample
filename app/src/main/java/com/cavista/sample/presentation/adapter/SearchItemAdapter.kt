package com.cavista.sample.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.adapter.SearchItemViewHolder
import com.cavista.sample.presentation.customeview.CustomRecyclerViewAdapter
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener

class SearchItemAdapter(context: Context) : CustomRecyclerViewAdapter<UISearchData, OnRecyclerObjectClickListener<UISearchData>, SearchItemViewHolder>(context) {

    override fun getId(position: Int): String {
        return getItemAt(position).id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            itemView = inflate(
                R.layout.search_item,
                parent,
                false
            ), adapter = this@SearchItemAdapter
        )
    }

}
