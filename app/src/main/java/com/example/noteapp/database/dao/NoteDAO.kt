package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note

@Dao
interface NoteDAO {
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query(value = "select * from note_table")
    fun getAllNote(): LiveData<List<Note>>

    /*@Query(value = "select * from note_table where title_col=:title")
    fun getAlNoteByTitle(title: String): LiveData<List<Note>>*/
}