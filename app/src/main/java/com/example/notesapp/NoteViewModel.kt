package com.example.notesapp

import androidx.lifecycle.ViewModel
import com.example.notesapp.GlobalNotes.notesList

class NoteViewModel: ViewModel() {


    fun fetchAllNotes() = GlobalNotes.notesList

    fun addNote(noteDescription: String){
        notesList.add(noteDescription)
    }

    fun updateNote(noteDescription: String, position: String){
        notesList.removeAt(position.toInt())
        notesList.add(noteDescription)
    }

    fun deleteNote(position: Int){
        notesList.removeAt(position)
    }


}