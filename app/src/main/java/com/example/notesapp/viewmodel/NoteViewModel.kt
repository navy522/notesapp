package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.utils.GlobalNotes
import com.example.notesapp.utils.GlobalNotes.notesList

class NoteViewModel: ViewModel() {

    fun fetchAllNotes() = GlobalNotes.notesList

    fun addNote(noteDescription: String){
        notesList.add(noteDescription)
    }

    fun updateNote(noteDescription: String, position: String){
        notesList.removeAt(position.toInt())
        notesList.add(position.toInt(), noteDescription)
    }

//    delete single note
    fun deleteNote(position: Int){
        notesList.removeAt(position)
    }

}