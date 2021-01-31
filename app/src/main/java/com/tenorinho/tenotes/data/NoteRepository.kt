package com.tenorinho.tenotes.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.tenorinho.tenotes.models.Note

class NoteRepository {
    private var noteDao:NoteDAO
    private var allNotes:LiveData<List<Note>>

    constructor(application:Application){
        val database:NoteDatabase = NoteDatabase.getDatabase(application)
        noteDao = database.getNoteDAO()
        allNotes = noteDao.getAll()
    }
    fun add(note:Note){
        InsertNoteAsyncTask(noteDao).execute(note)
    }
    fun update(note:Note){
        UpdateNoteAsyncTask(noteDao).execute(note)
    }
    fun delete(note:Note){
        DeleteNoteAsyncTask(noteDao).execute(note)
    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }

    private class InsertNoteAsyncTask:AsyncTask<Note, Unit, Unit>{
        val noteDao:NoteDAO
        constructor(noteDAO:NoteDAO){ this.noteDao = noteDAO }
        override fun doInBackground(vararg note:Note?) { note[0]?.apply{noteDao.add(this)} }
    }
    private class UpdateNoteAsyncTask:AsyncTask<Note, Unit, Unit>{
        val noteDao:NoteDAO
        constructor(noteDAO:NoteDAO){ this.noteDao = noteDAO }
        override fun doInBackground(vararg note:Note?) { note[0]?.apply{noteDao.update(this)} }
    }
    private class DeleteNoteAsyncTask:AsyncTask<Note, Unit, Unit>{
        val noteDao:NoteDAO
        constructor(noteDAO:NoteDAO){ this.noteDao = noteDAO }
        override fun doInBackground(vararg note:Note?) { note[0]?.apply{noteDao.delete(this)} }
    }
}