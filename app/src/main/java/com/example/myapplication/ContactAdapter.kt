package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView


import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.net.URL

class ContactAdapter(mContext:Context,private var data:MutableList<ContactEntity>?): RecyclerView.Adapter<ContactAdapter.MyViewHolder>()
{

    fun addAll(contact: List<ContactEntity>) {
        data!!.addAll(contact)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  data!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.contactname.text = this!!.data!![position].name
    }

    private var context:Context? = null


    init {

        context = mContext


    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        internal var contactname = itemView.findViewById<View>(R.id.tv_name) as TextView
        internal var image = itemView.findViewById<View>(R.id.ivProfile)
    }

}