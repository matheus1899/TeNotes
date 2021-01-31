package com.tenorinho.tenotes.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenorinho.tenotes.models.Note

//@Database(entities = arrayOf(Note::class), version = 1)
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun getNoteDAO():NoteDAO
    companion object{
        @Volatile private var INSTANCIA:NoteDatabase? = null
        fun getDatabase(context:Context):NoteDatabase{
            return INSTANCIA ?: synchronized(this){
                val instance:NoteDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "local_database"
                ).addCallback(DBCallback()).build()
                INSTANCIA = instance
                instance
            }
        }
    }
    private class DBCallback() : RoomDatabase.Callback() {
        override fun onCreate(db:SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}