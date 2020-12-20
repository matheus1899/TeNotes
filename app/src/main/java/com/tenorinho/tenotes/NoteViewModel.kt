package com.tenorinho.tenotes

import android.app.Application
import androidx.lifecycle.*
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.data.NoteRepository

class NoteViewModel:AndroidViewModel{
    private val repository:NoteRepository
    private var allNotes:LiveData<List<Note>>
    constructor(application:Application):super(application){
        repository = NoteRepository(application)
        allNotes = repository.getAllNotes()
    }
    fun add(note:Note){
        repository.add(note)
    }
    fun update(note:Note){
        repository.update(note)
    }
    fun delete(note:Note){
        repository.delete(note)
    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }
}