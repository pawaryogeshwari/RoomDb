package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_contact.*
import kotlinx.android.synthetic.main.contact_row.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



@SuppressLint("Registered")
class AddContactActivity:AppCompatActivity() {

    lateinit var mdb: AppDatabase
    lateinit var madapter: ContactAdapter
        var contactname: String? = null
    lateinit var contactList:List<ContactEntity>
    var username:String? = null
    val SELECTIMAGE = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)

        mdb = AppDatabase.getInstance(this)



       ivProfile.setOnClickListener{
           val intent = Intent()
           intent.type = "image/*"
           intent.action = Intent.ACTION_GET_CONTENT
           startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECTIMAGE)
       }




        btn_save.setOnClickListener {

            val thread = Thread {

                username  = addContact.text.toString()
                val addContact = ContactEntity(0, username!!)

                mdb!!.contactDao().addContact(addContact)

                Log.d("msg",""+username)

            }
            thread.start()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK)
            {
                if (requestCode == SELECTIMAGE)
                {
                    val selectedImageUri = data!!.data
                    ivProfile.setImageURI(selectedImageUri)

                }

                }
            }
        }






