package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.databinding.FragmentShowNoteBinding

class FragmentShowNote: Fragment(R.layout.fragment_show_note){
    var note:Note? = null;
    private var fragmentShowNoteBinding:FragmentShowNoteBinding? = null

    companion object{
        fun create():FragmentShowNote{
            return FragmentShowNote()
        }
    }
    override fun onViewCreated(view:View, savedInstanceState:Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding:FragmentShowNoteBinding = FragmentShowNoteBinding.bind(view)
        fragmentShowNoteBinding = binding
    }
    override fun onStart() {
        super.onStart()
        if(note != null){
            fragmentShowNoteBinding?.showNoteTitle?.text = note!!.title
            if(note!!.content.trimStart().trimEnd().isEmpty()){
                fragmentShowNoteBinding?.showNoteContent?.visibility = View.GONE
            }
            else{
                fragmentShowNoteBinding?.showNoteContent?.text = note!!.content
            }
        }
        else{
            fragmentShowNoteBinding?.showNoteTitle?.visibility = View.GONE
            fragmentShowNoteBinding?.showNoteContent?.visibility = View.GONE
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