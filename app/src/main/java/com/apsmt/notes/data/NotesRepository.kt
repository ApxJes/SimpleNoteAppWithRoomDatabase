package com.apsmt.notes.data

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val readAllNotes: LiveData<List<Notes>> = notesDao.readAllNotes()

    suspend fun addNotes(addNote: Notes) {
        notesDao.addNotes(addNote)
    }

    suspend fun updateNotes(updateNote: Notes) {
        notesDao.updateNotes(updateNote)
    }

    suspend fun deleteNotes(deleteNote: Notes) {
        notesDao.deleteNotes(deleteNote)
    }

    suspend fun deleteAllNotes() {
        notesDao.deleteAllNotes()
    }
}