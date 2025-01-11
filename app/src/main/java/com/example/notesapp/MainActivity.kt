package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        adapter = NoteAdapter {
            Log.d("MainActivityz", viewModel.fetchAllNotes().toString())

            val intent = Intent(this, AddEditNoteActivity::class.java)
            intent.putExtra("KEY_NOTE", GlobalNotes.notesList[it])
            startActivity(intent)
        }
        binding.rvNote.adapter = adapter

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        adapter.updateNotes(viewModel.fetchAllNotes())
    }
}