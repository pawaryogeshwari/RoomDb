package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.util.*


@SuppressLint("Registered")
class AddContactActivity:AppCompatActivity() {

    lateinit var mdb: AppDatabase
    lateinit var madapter: ContactAdapter
        var contactname: String? = null
    var image:String? = null
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


                val addContact = ContactEntity(0, username!!, this!!.image!!)

                mdb!!.contactDao().addContact(addContact)

                Log.d("msg",""+username)

            }
            thread.start()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    fun getContactBitmapFromURI(context: Context, uri: Uri): Bitmap? {
        try {
            val input = context.getContentResolver().openInputStream(uri) ?: return null
            return BitmapFactory.decodeStream(input)
        } catch (e: FileNotFoundException) {

        }

        return null

    }



    fun saveBitmapIntoSDCardImage(context: Context, finalBitmap: Bitmap): File {
        val mFolder = File("${getExternalFilesDir(null)?.absolutePath}/sample")
        val imgFile = File(mFolder.absolutePath + "/${System.currentTimeMillis()}.png")
        if (!mFolder.exists()) {
            mFolder.mkdir()
        }
        if (!imgFile.exists()) {
            imgFile.createNewFile()
        }

        try {
            val out = FileOutputStream(imgFile)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            Log.d("Check ","Img"+imgFile)
            out.flush()
            out.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imgFile
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






