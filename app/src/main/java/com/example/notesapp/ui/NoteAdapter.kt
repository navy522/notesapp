package com.example.notesapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.R
import com.example.notesapp.utils.GlobalNotes

class NoteAdapter(
    private val onEditClick: (position: Int) -> Unit,
    private val onDeleteClick: (position: Int) -> Unit
): RecyclerView.Adapter<MyViewHolder>() {

    var itemList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowitem_notes, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvNote.text = itemList[position]

        holder.ivEdit.setOnClickListener {
            onEditClick(position)
        }

        holder.ivDelete.setOnClickListener {
            onDeleteClick(position)
        }

    }

   fun updateNotes(notesList: ArrayList<String>){
       itemList.clear()

       notesList.forEach {
           itemList.add(it)
       }

       notifyDataSetChanged()
   }

    fun sortNote(sort: Boolean) {

        if (sort){
            GlobalNotes.notesList.sortDescending()
            itemList.sortDescending()
        }else{
            GlobalNotes.notesList.sort()
            itemList.sort()
        }
        notifyDataSetChanged()
    }
}

class MyViewHolder(itemView: View): ViewHolder(itemView) {

    val tvNote = itemView.findViewById<TextView>(R.id.tvNote)
    val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
    val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
}
