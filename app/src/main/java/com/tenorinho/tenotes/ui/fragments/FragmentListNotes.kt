package com.tenorinho.tenotes.ui.fragments

import android.app.AlertDialog
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
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.ui.INavigation
import com.tenorinho.tenotes.ui.adapters.NoteAdapter

class FragmentListNotes : Fragment(){
    var fab:FloatingActionButton? = null
    var recyclerView:RecyclerView? = null
    private var noteViewModel:NoteViewModel? = null
    companion object{
        fun create():FragmentListNotes{
            return FragmentListNotes()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_notes, parent, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab = activity?.findViewById(R.id.list_note_fab)
        val listener:INavigation = activity as INavigation
        fab?.setOnClickListener { listener.navigateToAddNoteFragment() }
        recyclerView = activity?.findViewById(R.id.list_note_recyclerview)
        activity?.registerForContextMenu(recyclerView)
        val adapter:NoteAdapter = getAdapter()
        noteViewModel = ViewModelProviders.of(activity!!).get(NoteViewModel::class.java)
        noteViewModel?.getAllNotes()?.observe(this, Observer<List<Note>> {
            //Thread.sleep(150)
            adapter.setNotes(it)
        })
        recyclerView?.adapter = adapter
    }
    private fun getAdapter():NoteAdapter{
        return NoteAdapter(activity!!)
    }
    fun openEdit(position: Int){
        val act = activity
        if(act != null && act is INavigation){
            val adapter:NoteAdapter = recyclerView?.adapter as NoteAdapter
            val note:Note = adapter.array[position]
            act.navigateToEditNoteFragment(note)
        }
    }
    fun openDelete(position: Int){
        val adapter:NoteAdapter = recyclerView?.adapter as NoteAdapter
        val note:Note? = adapter.array[position]
        if(note != null){
            val dialog:AlertDialog.Builder = AlertDialog.Builder(activity!!).setTitle("Delete?").setMessage("Você tem certeza que deseja excluir?")
            dialog.setNegativeButton("No", null)
            dialog.setPositiveButton("Yes, delete", DialogInterface.OnClickListener { dialogInterface, i ->
                noteViewModel?.delete(note)
                Toast.makeText(activity!!, "Note has deleted", Toast.LENGTH_SHORT).show()
            })
            dialog.show()
        }
    }
}