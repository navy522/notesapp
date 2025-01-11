package com.example.notesapp

import androidx.lifecycle.ViewModel
import com.example.notesapp.GlobalNotes.notesList

class NoteViewModel: ViewModel() {


    fun fetchAllNotes() = GlobalNotes.notesList

    fun addNote(noteDescription: String){
        notesList.add(noteDescription)
    }

}