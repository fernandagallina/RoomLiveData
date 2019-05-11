package com.fernanda.myapplication

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun onBind(item: Item, listener: ItemListAdapter.OnItemClickedListener) {
        textView.text = item.item
        deleteButton.setOnClickListener { listener.onItemClicked(item, DaoOperation.DELETE) }
    }
}