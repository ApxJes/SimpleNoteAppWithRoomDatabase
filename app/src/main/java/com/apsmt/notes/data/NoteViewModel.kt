package com.apsmt.notes.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllNotes: LiveData<List<Notes>>
    val repository: NotesRepository

    init {
        val notesDao = NoteDatabase.getData(application).notesDao()
        repository = NotesRepository(notesDao)
        readAllNotes = repository.readAllNotes
    }

    fun addNotes(addNote: Notes) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.addNotes(addNote)
        }
    }

    fun updateNotes(updateNote: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(updateNote)
        }
    }

    fun deleteNotes(deleteNotes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(deleteNotes)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }
}