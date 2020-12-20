package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tenorinho.tenotes.*
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.data.*

class FragmentEditNote: Fragment() {
    var note:Note? = null
    var editTitle:EditText? = null
    var editContent:EditText? = null
    private var noteViewModel:NoteViewModel? = null

    companion object{
        fun create():FragmentEditNote{
            return FragmentEditNote()
        }
    }
    private fun popAddFragment(){
        val act = activity
        act?.supportFragmentManager?.popBackStack()
    }
    private fun editNote(){
        if(note != null){
            val title:String = editTitle?.text.toString()
            val content:String = editContent?.text.toString()

            note!!.title = title
            note!!.note = content
            noteViewModel?.update(note!!)
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        noteViewModel = ViewModelProviders.of(activity!!).get(NoteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null && savedInstanceState.containsKey("Note")){
            note = savedInstanceState.getSerializable("Note") as Note
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.edit_note_close_fab)?.setOnClickListener{popAddFragment()}
        view.findViewById<FloatingActionButton>(R.id.edit_note_save_fab)?.setOnClickListener{editNote()}
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onStart() {
        editTitle = view?.findViewById(R.id.edit_note_title)
        editContent = view?.findViewById(R.id.edit_note_content)

        editTitle?.setText(note?.title)
        editContent?.setText(note?.note)
        super.onStart()
    }
}