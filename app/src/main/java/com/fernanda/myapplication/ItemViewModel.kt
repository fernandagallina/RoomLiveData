package com.fernanda.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class ItemViewModel(application: Application) : AndroidViewModel(application), ItemListAdapter.OnItemClickedListener {

    private var repository: ItemRepository = ItemRepository(application)
    private var items: LiveData<List<Item>>

    init {
        items = repository.getItems()
    }

    fun getItems(): LiveData<List<Item>> {
        return items
    }

    fun insert(item: Item) {
        repository.insert(item)
    }

    fun deleteAllItems() {
        repository.deleteAll()
    }

    override fun onItemClicked(item: Item, daoOp: DaoOperation) {
        if (daoOp == DaoOperation.DELETE) {
            repository.delete(item)
        }
    }
}
