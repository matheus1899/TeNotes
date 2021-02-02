package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProviders
import com.tenorinho.tenotes.*
import com.tenorinho.tenotes.databinding.FragmentEditNoteBinding
import com.tenorinho.tenotes.models.Note

class FragmentEditNote: Fragment(R.layout.fragment_edit_note) {
    private var fragmentEditNoteBinding:FragmentEditNoteBinding? = null
    private val noteViewModel:NoteViewModel by lazy { ViewModelProviders.of(activity!!).get(NoteViewModel::class.java) }
    private var note:Note? = null

    companion object{
        fun create():FragmentEditNote{
            return FragmentEditNote()
        }
        fun create(note:Note?):FragmentEditNote{
            val fragment = FragmentEditNote()
            fragment.note = note
            return fragment
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentEditNoteBinding = FragmentEditNoteBinding.bind(view)
        fragmentEditNoteBinding?.editNoteCloseFab?.setOnClickListener{popAddFragment()}
        fragmentEditNoteBinding?.editNoteSaveFab?.setOnClickListener{editNote()}
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onStart(){
        fragmentEditNoteBinding?.title = note?.title
        fragmentEditNoteBinding?.content = note?.content
        super.onStart()
    }
    private fun popAddFragment(){
        val act:FragmentActivity? = activity
        act?.supportFragmentManager?.popBackStack()
    }
    private fun editNote(){
        if(note != null){
            note!!.title = fragmentEditNoteBinding?.title ?: ""
            note!!.content = fragmentEditNoteBinding?.content ?: ""
            noteViewModel.update(note!!)
            Toast.makeText(activity, "Editado com sucesso", Toast.LENGTH_SHORT).show()
            popAddFragment()
        }
        else{
            return
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("Note", note)
        super.onSaveInstanceState(outState)
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null && savedInstanceState.containsKey("Note")){
            note = savedInstanceState.getSerializable("Note") as Note
        }
    }
}