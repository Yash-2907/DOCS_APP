package com.example.ashokadocs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentProviderOperation
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class Adapter(private val userList: ArrayList<docs>) : RecyclerView.Adapter<Adapter.MyViewHolder>(){
    private lateinit var cpyListener: onItemClickListener
    interface onItemClickListener{
        fun onCpyClick(position: Int,oper: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener)
    {
        cpyListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.docitem,parent,false)
        return MyViewHolder(itemView,cpyListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem=userList[position]

        holder.heading.text=currentitem.Title
        holder.copylink.contentDescription=currentitem.Link
        holder.status.contentDescription=currentitem.Status
        holder.download.contentDescription=currentitem.Drive
        Picasso.get().load(currentitem.Image).into(holder.Image)
    }

    class MyViewHolder(itemView : View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val heading: TextView= itemView.findViewById(R.id.titlebox)
        val copylink: ImageButton= itemView.findViewById(R.id.linkbtn)
        val status: ImageButton= itemView.findViewById(R.id.graphbtn)
        val download: ImageButton= itemView.findViewById(R.id.downloadbtn)
        val Image:ImageView=itemView.findViewById(R.id.imagebox)

        init{

            copylink.setOnClickListener{
                listener.onCpyClick(adapterPosition,0)
            }

            download.setOnClickListener{
                listener.onCpyClick(adapterPosition,1)
            }

            status.setOnClickListener{
                listener.onCpyClick(adapterPosition,2)
            }
        }
    }

}