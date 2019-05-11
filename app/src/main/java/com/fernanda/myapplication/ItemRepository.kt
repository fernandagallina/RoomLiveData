package com.fernanda.myapplication

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class ItemRepository constructor(application: Application) {

    private var itemsDao: ItemDao
    private var itemsList: LiveData<List<Item>>

    init {
        val database = ItemRoomDatabase.getDatabase(application)
        itemsDao = database.itemDao()
        itemsList = itemsDao.getItemList()
    }

    fun getItems(): LiveData<List<Item>> {
        return itemsList
    }

    fun insert(item: Item) {
        NewAsyncTask(itemsDao, DaoOperation.INSERT).execute(item)
    }

    fun delete(item: Item) {
        NewAsyncTask(itemsDao, DaoOperation.DELETE).execute(item)
    }

    fun deleteAll() {
        NewAsyncTask(itemsDao, DaoOperation.DELETE_ALL).execute()
    }

    companion object {

        private class NewAsyncTask constructor(private val asyncDao: ItemDao, private val daoOperation: DaoOperation) :
            AsyncTask<Item, Void, Void>() {
            override fun doInBackground(vararg params: Item): Void? {
                when (daoOperation) {
                    DaoOperation.INSERT -> asyncDao.insert(params[0])
                    DaoOperation.DELETE -> asyncDao.delete(params[0])
                    DaoOperation.DELETE_ALL -> asyncDao.deleteAllItems()
                }
                return null
            }
        }
    }
}
