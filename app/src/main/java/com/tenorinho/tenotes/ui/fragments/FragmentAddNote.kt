package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProviders
import com.tenorinho.tenotes.*
import com.tenorinho.tenotes.databinding.FragmentAddNoteBinding
import com.tenorinho.tenotes.models.Note

class FragmentAddNote:Fragment(R.layout.fragment_add_note) {
    //var note:Note=Note(title="",content="")
    private var fragmentAddNoteBinding:FragmentAddNoteBinding? = null
    private val noteViewModel:NoteViewModel by lazy{
        ViewModelProviders.of(activity!!).get(NoteViewModel::class.java)
    }
    companion object{
        fun create():FragmentAddNote{
            return FragmentAddNote()
        }
    }
    override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAddNoteBinding = FragmentAddNoteBinding.bind(view)
        fragmentAddNoteBinding?.addNoteCloseFab?.setOnClickListener{popAddFragment()}
        fragmentAddNoteBinding?.addNoteSaveFab?.setOnClickListener{addNote()}
        fragmentAddNoteBinding?.title = ""
        fragmentAddNoteBinding?.content = ""
    }
    private fun popAddFragment(){
        val act:FragmentActivity? = activity
        act?.supportFragmentManager?.popBackStack()
    }
    private fun addNote(){
        val title:String = fragmentAddNoteBinding?.title?.trimStart()?.trimEnd() ?: ""
        val content:String = fragmentAddNoteBinding?.content?.trimStart()?.trimEnd() ?: ""
        if(title.isEmpty() || title.isBlank()) {
            Toast.makeText(activity, "Título é necessario", Toast.LENGTH_SHORT).show()
            return
        }
        else if(title.length > 40){
            Toast.makeText(activity, "Título deve ter no máximo 40 caracteres", Toast.LENGTH_SHORT).show()
            return
        }
        noteViewModel.add(Note(title= title, content = content))
        popAddFragment()
        Toast.makeText(activity, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
    }
}
