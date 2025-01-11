package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class NoteAdapter(): RecyclerView.Adapter<MyViewHolder>() {

    var itemList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowitem_notes, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvNote.text = itemList[position]
        holder.tvNote.text = "Data $position"
    }
}

class MyViewHolder(itemView: View): ViewHolder(itemView) {

    val tvNote = itemView.findViewById<TextView>(R.id.tvNote)
}
