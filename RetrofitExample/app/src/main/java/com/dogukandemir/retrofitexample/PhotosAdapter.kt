package com.dogukandemir.retrofitexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PhotosAdapter(val photosList : ArrayList<PhotosModel>,tiklama:Click):RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    val renkler:Array<String> = arrayOf("#a52a2a","#1c6071") //renkler means colors

    val tiklanma =tiklama

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val txtAlbumId = itemView.findViewById<TextView>(R.id.textviewAlbumId)
        val txtId = itemView.findViewById<TextView>(R.id.textviewId)
        val txtTitle = itemView.findViewById<TextView>(R.id.textviewTitle)
        val image = itemView.findViewById<ImageView>(R.id.image)

       fun changeBackColor(renkler : Array<String>,position : Int){
         itemView.setBackgroundColor(Color.parseColor(renkler[position % 2]))
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  =LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return photosList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoItem:PhotosModel = photosList.get(position)
   //     holder.itemView.setBackgroundColor(Color.parseColor(renkler[position % 2]))
        holder.changeBackColor(renkler,position)
        holder.txtAlbumId.text = photoItem.albumId.toString()
        holder.txtId.text= photoItem.id.toString()
        holder.txtTitle.text = photoItem.title

        Picasso.get().load(photoItem.url).into(holder.image)

        holder.itemView.setOnClickListener {
            tiklanma.click(position)
        }



    }
}
