package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.R

class FragmentShowNote: Fragment(){
    var note:Note? = null;
    companion object{
        fun create():FragmentShowNote{
            return FragmentShowNote()
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_note, container, false)
    }
    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.show_note_title)?.text = note?.title
        view?.findViewById<TextView>(R.id.show_note_content)?.text = note?.note
    }
}