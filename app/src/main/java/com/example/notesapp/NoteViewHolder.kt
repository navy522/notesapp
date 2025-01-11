package com.example.notesapp

import androidx.lifecycle.ViewModel

class NoteViewHolder: ViewModel() {

    var notesList =   mutableListOf<String>()

    fun fetchAllNotes(){

    }

    fun addNote(noteDescription: String){
        notesList.add(noteDescription)
    }

}