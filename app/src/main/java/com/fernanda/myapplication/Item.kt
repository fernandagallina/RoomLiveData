package com.fernanda.myapplication

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "item_table")
class Item (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")
    val item: String
)
