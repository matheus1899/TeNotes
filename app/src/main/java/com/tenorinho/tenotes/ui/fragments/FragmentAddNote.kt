package com.tenorinho.tenotes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenorinho.tenotes.R

class FragmentAddNote:Fragment() {
    companion object{
        fun Create():FragmentAddNote{
            return FragmentAddNote()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_note, parent, false)
    }
}