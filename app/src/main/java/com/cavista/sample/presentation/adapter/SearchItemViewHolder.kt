package com.cavista.sample.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cavista.sample.R
import com.cavista.sample.domain.model.UISearchData
import com.cavista.sample.presentation.customeview.BaseViewHolder
import com.cavista.sample.presentation.customeview.OnRecyclerObjectClickListener
import com.cavista.sample.presentation.utils.AppUtils


class SearchItemViewHolder(itemView: View) :
    BaseViewHolder<UISearchData, OnRecyclerObjectClickListener<UISearchData>>(itemView) {

    private val itemImageView = itemView.findViewById<ImageView>(R.id.itemImgView)
    private val itemTitleName = itemView.findViewById<TextView>(R.id.titleName)

    override fun onBind(
        item: UISearchData,
        listener: OnRecyclerObjectClickListener<UISearchData>,
        position: Int
    ) {
        itemTitleName.text = item.title
        if (!item.imageUrl.isNullOrEmpty()) {
            AppUtils.displayImage(itemView.context, item.imageUrl, itemImageView)
        }
        itemView.setOnClickListener {
            listener.onRowClicked(item, position)
        }
    }

}