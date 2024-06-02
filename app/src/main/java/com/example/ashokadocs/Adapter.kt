package com.example.ashokadocs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(private val userList: ArrayList<docs>) : RecyclerView.Adapter<Adapter.MyViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.docitem,parent,false)
        return MyViewHolder(itemView)
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

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val heading: TextView= itemView.findViewById(R.id.titlebox)
        val copylink: ImageButton= itemView.findViewById(R.id.linkbtn)
        val status: ImageButton= itemView.findViewById(R.id.graphbtn)
        val download: TextView= itemView.findViewById(R.id.downloadbtn)
        val Image:ImageView=itemView.findViewById(R.id.imagebox)

    }
}