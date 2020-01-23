package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactName")
data class ContactEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "NAME")var name:String,
    @ColumnInfo(name = "IMAGE")var img:String

    )


