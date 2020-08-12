package com.cavista.sample.presentation.adapter

import android.view.View
import android.widget.ImageView
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.customeview.BaseViewHolder
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener

class SearchItemViewHolder(itemView: View, val adapter: SearchItemAdapter) :
    BaseViewHolder<UISearchData, OnRecyclerObjectClickListener<UISearchData>>(itemView) {

    private val itemImageView = itemView.findViewById<ImageView>(R.id.itemImgView)

    override fun onBind(
        item: UISearchData,
        listener: OnRecyclerObjectClickListener<UISearchData>,
        position: Int
    ) {
        itemView.setOnClickListener {
            listener.onRowClicked(item, position)
        }
    }

}