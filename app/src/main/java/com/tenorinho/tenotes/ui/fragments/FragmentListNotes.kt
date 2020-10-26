package com.tenorinho.tenotes.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.data.NoteRepository
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.ui.INavigation
import com.tenorinho.tenotes.ui.adapters.NoteAdapter

class FragmentListNotes : Fragment(){
    var fab:FloatingActionButton? = null
    var recyclerView:RecyclerView? = null

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
        val listener = activity as INavigation
        fab?.setOnClickListener { listener.navigateToAddNoteFragment() }
        recyclerView = activity?.findViewById(R.id.list_note_recyclerview)
        activity?.registerForContextMenu(recyclerView)
        recyclerView?.adapter = getAdapter()
    }
    private fun getAdapter():NoteAdapter{
        return NoteAdapter(activity!!, NoteRepository(activity!!).getAll())
    }
    fun openEdit(position: Int){
        val act = activity
        if(act != null && act is INavigation){
            val adapter = recyclerView?.adapter as NoteAdapter
            val note = adapter.array?.get(position)
            act.navigateToEditNoteFragment(note)
        }
    }
    fun openDelete(position: Int){
        val adapter = recyclerView?.adapter as NoteAdapter
        val note = adapter.array?.get(position)
        if(note != null){
            val dialog = AlertDialog.Builder(activity!!).setTitle("Delete?").setMessage("Você tem certeza que deseja excluir?")
            dialog.setNegativeButton("No", null)
            dialog.setPositiveButton("Yes, delete", DialogInterface.OnClickListener { dialogInterface, i ->
                val repo = NoteRepository(activity!!)
                repo.delete(note)
                adapter.array?.remove(note)
                adapter.notifyItemRemoved(position)
                Toast.makeText(activity!!, "Note has deleted", Toast.LENGTH_SHORT).show()
            })
            dialog.show()
        }
    }
}