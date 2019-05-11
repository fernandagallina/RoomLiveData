package com.fernanda.myapplication

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class ItemListAdapter(private val onItemClickedListener: OnItemClickedListener) : RecyclerView.Adapter<ItemViewHolder>() {

    private  var items: List<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.onBind(items[position], onItemClickedListener)

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnItemClickedListener {

        fun onItemClicked(item: Item, daoOp: DaoOperation)
    }
}
