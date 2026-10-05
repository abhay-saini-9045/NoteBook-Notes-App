package com.example.noteapp.ui.screens.notesScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.local.Note

@Composable
fun NoteScreen(
    viewModel: NotesViewModel,
    onOpenNote: (Note) -> Unit,
    editingNote: Note? = null,
    onDoneEditing: () -> Unit
) {

    // get note at real time from database
    val notes by viewModel.notes.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var currentEditing by remember { mutableStateOf<Note?>(null) }

    LaunchedEffect(editingNote) {
        if (editingNote != null) {
            currentEditing = editingNote
            title = editingNote.title
            content = editingNote.content
            onDoneEditing() // editing note reset after do

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = if (currentEditing == null) "Add Note" else " Update Note",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            placeholder = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            maxLines = 3,
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    if (currentEditing == null) {
                        viewModel.addNote(title, content)
                    } else {
                        viewModel.updateNote(
                            currentEditing!!.copy(
                                title = title, content = content
                            )
                        )
                        currentEditing = null
                    }
                    title = ""
                    content = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (currentEditing == null) "Add Note" else " Update Note")

        }

        Spacer(Modifier.height(12.dp))

        Text(
            "Your Notes:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            items(notes) { note ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onOpenNote(note)
                        },
                    color = Color(note.color),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = note.content.take(20) + if (note.content.length > 20) "..." else "",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

        }
    }
}