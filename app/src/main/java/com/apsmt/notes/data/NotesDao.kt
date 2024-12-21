package com.apsmt.notes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(addNote: Notes)

    @Update
    suspend fun updateNotes(updateNote: Notes)

    @Delete
    suspend fun deleteNotes(deleteNote: Notes)

    @Query("DELETE FROM note")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun readAllNotes(): LiveData<List<Notes>>

}