package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.data.local.Note
import com.example.noteapp.data.local.NoteDatabase
import com.example.noteapp.data.repository.NoteRepository
import com.example.noteapp.ui.screens.notesScreen.NoteScreen
import com.example.noteapp.ui.screens.notesScreen.NotesViewModel
import com.example.noteapp.ui.screens.readNoteScreen.ReadNoteScreen
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                val db = NoteDatabase.getDatabase(this)
                val repository = NoteRepository(db.noteDao())
                val factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return NotesViewModel(repository) as T
                    }
                }

                val viewModel: NotesViewModel = viewModel(factory = factory)
                var selectedNote by remember { mutableStateOf<Note?>(null) }
                var editingNote by remember { mutableStateOf<Note?>(null) }

                if (selectedNote == null) {
                    NoteScreen(
                        viewModel = viewModel,
                        onOpenNote = { note -> selectedNote = note },
                        editingNote = editingNote,
                        onDoneEditing = { editingNote = null }
                    )
                } else {
                    ReadNoteScreen(
                        note = selectedNote!!,
                        onBack = { selectedNote = null },
                        onEdit = { note ->
                            editingNote = note
                            selectedNote = null
                        },
                        onDelete = { note ->
                            viewModel.deleteNote(note)
                            selectedNote = null
                        }
                    )
                }
            }
        }
    }
}