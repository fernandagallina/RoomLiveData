package com.fernanda.myapplication

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase {
            if (INSTANCE == null) {
                synchronized(ItemRoomDatabase::class.java) {
                    if (INSTANCE == null) {

                        //Acquire an instance of the database//
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ItemRoomDatabase::class.java, "item_list_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
