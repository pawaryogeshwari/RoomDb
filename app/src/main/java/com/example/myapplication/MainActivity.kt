package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_contact.*

class MainActivity : AppCompatActivity() {

    lateinit var madapter: ContactAdapter
    var contactname: String? = null
    var contactList:List<ContactEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        lateinit var mdb: AppDatabase

        fab.setOnClickListener {
            var intent = Intent(this,AddContactActivity::class.java)
            startActivity(intent)
        }



        madapter = ContactAdapter(this, mutableListOf())
        rv_list.adapter = madapter


        val thread = Thread {

            mdb = AppDatabase.getInstance(this)
           val contacts = mdb.contactDao().getContact()


          runOnUiThread {
              madapter.addAll(contacts)
              madapter.notifyDataSetChanged()
               Log.d("listOfContact",""+contacts)
          }
       }
        thread.start()


    }




}
