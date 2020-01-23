package com.example.myapplication

import android.media.Image
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ContactDao
{
    @Insert
        fun addContact(add:ContactEntity)

        @Query("select * From  ContactName")
        fun getContact():List<ContactEntity>


    }
