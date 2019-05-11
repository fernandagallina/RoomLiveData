package com.fernanda.myapplication

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ItemDao {

    @Query("SELECT * from item_table ")
    fun getItemList(): LiveData<List<Item>>

    @Query("DELETE from item_table")
    fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)
}
