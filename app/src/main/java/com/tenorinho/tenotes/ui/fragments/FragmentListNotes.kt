package com.tenorinho.tenotes.ui.fragments

import android.app.*
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tenorinho.tenotes.*
import com.tenorinho.tenotes.data.*
import com.tenorinho.tenotes.databinding.FragmentListNotesBinding
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.ui.INavigation
import com.tenorinho.tenotes.ui.adapters.NoteAdapter

class FragmentListNotes : Fragment(R.layout.fragment_list_notes){
    private val noteViewModel:NoteViewModel by lazy {ViewModelProviders.of(activity!!).get(NoteViewModel::class.java)}
    private var fragmentListNotesBinding:FragmentListNotesBinding? = null
    companion object{
        fun create():FragmentListNotes{
            return FragmentListNotes()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentListNotesBinding = FragmentListNotesBinding.bind(view)
        val listener:INavigation = activity as INavigation
        fragmentListNotesBinding?.listNoteFab?.setOnClickListener{listener.navigateToAddNoteFragment()}
        activity?.registerForContextMenu(fragmentListNotesBinding?.listNoteRecyclerview)
        val adapter:NoteAdapter = getAdapter()
        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
            adapter.setNotes(it)
        })
        fragmentListNotesBinding?.listNoteRecyclerview?.adapter = adapter
    }
    private fun getAdapter():NoteAdapter{
        return NoteAdapter(activity!!)
    }
    fun openEdit(position: Int){
        val act:Activity? = activity
        if(act != null && act is INavigation){
            val adapter:NoteAdapter = fragmentListNotesBinding?.listNoteRecyclerview?.adapter as NoteAdapter
            val note:Note = adapter.array[position]
            act.navigateToEditNoteFragment(note)
        }
    }
    fun openDelete(position: Int){
        val adapter:NoteAdapter = fragmentListNotesBinding?.listNoteRecyclerview?.adapter as NoteAdapter
        val note:Note? = adapter.array[position]
        if(note != null){
            val dialog:AlertDialog.Builder = AlertDialog.Builder(activity!!).setTitle("Delete?").setMessage("Você tem certeza que deseja excluir?")
            dialog.setNegativeButton("No", null)
            dialog.setPositiveButton("Yes, delete", DialogInterface.OnClickListener { dialogInterface, i ->
                noteViewModel.delete(note)
                Toast.makeText(activity!!, "Note has deleted", Toast.LENGTH_SHORT).show()
            })
            dialog.show()
        }
    }
}