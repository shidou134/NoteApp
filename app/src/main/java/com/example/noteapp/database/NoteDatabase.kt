package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.database.dao.NoteDAO
import com.example.noteapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDAO

    companion object {
        @Volatile
        private var instant: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instant == null) {
                instant =
                    Room.databaseBuilder(context, NoteDatabase::class.java, "NoteDatabase").build()
            }
            return instant!!
        }
    }
}