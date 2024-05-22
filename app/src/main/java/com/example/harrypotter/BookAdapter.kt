package com.example.harrypotter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(val liste : ArrayList<BookDataModel>) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindData(liste[position])
    }
}