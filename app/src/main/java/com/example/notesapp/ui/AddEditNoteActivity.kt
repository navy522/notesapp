package com.example.notesapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.utils.GlobalNotes
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var viewModel: NoteViewModel
    private var notePosition: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        notePosition = intent.getStringExtra("KEY_NOTE")


        if (notePosition.toString().trim().isNotEmpty() && notePosition != null){
                binding.etNote.setText(GlobalNotes.notesList[notePosition!!.toInt()])
        }

        binding.btnAddNote.setOnClickListener {
            if (binding.etNote.text.trim().isNotEmpty() && notePosition.toString().trim().isNotEmpty() && notePosition != null){
                viewModel.updateNote(binding.etNote.text.trim().toString(), notePosition!!)
                finish()
            }

            else if (binding.etNote.text.trim().isNotEmpty()){
                viewModel.addNote(binding.etNote.text.trim().toString())
                finish()
            }
        }
    }
}