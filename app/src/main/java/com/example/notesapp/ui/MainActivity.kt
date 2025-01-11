package com.example.notesapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.utils.GlobalNotes
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var viewModel: NoteViewModel
    private var sort = false

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

        adapter = NoteAdapter({ position->
            val intent = Intent(this, AddEditNoteActivity::class.java)
            intent.putExtra("KEY_NOTE", position.toString())
            startActivity(intent)
        },{position ->
            viewModel.deleteNote(position)
            val allNotes = viewModel.fetchAllNotes()
            adapter.updateNotes(allNotes)
            toggleUIVisibility(allNotes)
        })

        binding.rvNote.adapter = adapter

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddEditNoteActivity::class.java))
        }

        binding.ivSort.setOnClickListener {
            adapter.sortNote(sort)
            sort = !sort
        }

        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.trim().toString().isNotEmpty() && binding.etSearch.text.trim().toString() != null){
                for(i in 0 until GlobalNotes.notesList.size){
                    if (GlobalNotes.notesList[i].contains(binding.etSearch.text.trim().toString())){
                    }
                }
            }
        }
    }

    private fun toggleUIVisibility(allNotes: ArrayList<String>) {
        if (allNotes.size == 0){
            binding.rvNote.visibility = View.GONE
            binding.tvNoRecord.visibility = View.VISIBLE
        }else{
            binding.rvNote.visibility = View.VISIBLE
            binding.tvNoRecord.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()

//        re-fetching all notes and updating in the ui
        val allNotes = viewModel.fetchAllNotes()
        adapter.updateNotes(allNotes)
        toggleUIVisibility(allNotes)
    }
}