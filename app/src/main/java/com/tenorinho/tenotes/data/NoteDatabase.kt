package com.tenorinho.tenotes.data

import androidx.room.*
import com.tenorinho.tenotes.models.Note

//@Database(entities = arrayOf(Note::class), version = 1)
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun getNoteDAO():NoteDAO
}