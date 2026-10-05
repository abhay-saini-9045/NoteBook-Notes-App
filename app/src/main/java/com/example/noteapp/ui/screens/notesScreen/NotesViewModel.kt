package com.example.noteapp.ui.screens.notesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.local.Note
import com.example.noteapp.data.repository.NoteRepository
import com.example.noteapp.util.ColorUtil
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NoteRepository) : ViewModel() {

    val notes = repository.getAllNotes()
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addNote(title: String, content: String) = viewModelScope.launch {

        val color = ColorUtil.getRandomColor()

        repository.addNote(Note(title = title, content = content, color = color))
    }

    fun updateNote(note: Note) = viewModelScope.launch {

        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {

        repository.deleteNote(note)
    }

}