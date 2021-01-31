package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tenorinho.tenotes.*
import com.tenorinho.tenotes.models.Note

class FragmentAddNote:Fragment() {
    var edtTitle: EditText? = null
    var edtContent: EditText? = null
    private val noteViewModel:NoteViewModel by lazy{
        ViewModelProviders.of(activity!!).get(NoteViewModel::class.java)
    }
    companion object{
        fun create():FragmentAddNote{
            return FragmentAddNote()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_add_note, parent, false)
        view.findViewById<FloatingActionButton>(R.id.add_note_close_fab).setOnClickListener{popAddFragment()}
        view.findViewById<FloatingActionButton>(R.id.add_note_save_fab).setOnClickListener{addNote()}
        edtTitle = view.findViewById(R.id.add_note_title)
        edtContent = view.findViewById(R.id.add_note_content)
        return view
    }
    private fun popAddFragment(){
        val act:FragmentActivity? = activity
        act?.supportFragmentManager?.popBackStack()
    }
    private fun addNote(){
        val title:String = edtTitle?.text.toString().trimStart().trimEnd()
        val content:String = edtContent?.text.toString()
        if(title.isEmpty() || title.isBlank()) {
            Toast.makeText(activity, "Título é necessario", Toast.LENGTH_SHORT).show()
            return
        }
        else if(title.length > 50){
            Toast.makeText(activity, "Título deve ter no máximo 40 caracteres", Toast.LENGTH_SHORT).show()
            return
        }
        val note = Note(title=title, content = content)
        noteViewModel.add(note)
        Toast.makeText(activity, "Adicionado com sucesso", Toast.LENGTH_SHORT).show()
        popAddFragment()
    }
}
