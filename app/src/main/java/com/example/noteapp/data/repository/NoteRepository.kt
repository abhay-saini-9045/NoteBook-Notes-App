package com.example.noteapp.data.repository

import com.example.noteapp.data.local.Note
import com.example.noteapp.data.local.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {

    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()

    suspend fun addNote(note: Note) = dao.insert(note)
    suspend fun updateNote(note: Note) = dao.update(note)
    suspend fun deleteNote(note: Note) = dao.delete(note)

}