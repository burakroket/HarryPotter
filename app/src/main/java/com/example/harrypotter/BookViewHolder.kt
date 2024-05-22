package com.example.harrypotter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookViewHolder(val container : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(R.layout.books_item,container,false)
){
    fun bindData(data:BookDataModel){
        val tvBookName = itemView.findViewById<TextView>(R.id.tvBookName)
        val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
        val releaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        val coverImage = itemView.findViewById<ImageView>(R.id.imgBook)


        tvBookName.text = data.bookName
        tvAuthor.text = data.author
        releaseDate.text = data.releaseDate
        Glide.with(itemView.context)
            .load(data.coverPhoto)
            .into(coverImage)
    }
}