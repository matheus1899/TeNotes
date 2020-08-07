package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.ui.INavigation

class FragmentListNotes : Fragment(){
    var fab:FloatingActionButton? = null
    companion object{
        fun Create():FragmentListNotes{
            return FragmentListNotes()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_notes, parent, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        fab = activity?.findViewById(R.id.list_note_fab)
        var listener = activity as INavigation
        fab?.setOnClickListener { listener.navigateToAddNoteFragment() }
        super.onActivityCreated(savedInstanceState)
    }
}