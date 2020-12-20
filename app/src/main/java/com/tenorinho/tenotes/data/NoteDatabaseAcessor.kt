package com.tenorinho.tenotes.data

import android.content.Context
import androidx.room.Room

class NoteDatabaseAcessor {
    companion object{
        private lateinit var noteDatabaseInstance : NoteDatabase
        private val notesDBName = "TeNotesDB"
        fun getInstance(context:Context):NoteDatabase{
            noteDatabaseInstance = Room.databaseBuilder(context, NoteDatabase::class.java, notesDBName).build()
            return  noteDatabaseInstance
        }
    }
}