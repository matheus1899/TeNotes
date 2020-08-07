package com.tenorinho.tenotes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.ui.INavigation
import com.tenorinho.tenotes.ui.fragments.FragmentAddNote
import com.tenorinho.tenotes.ui.fragments.FragmentListNotes

class MainActivity : AppCompatActivity(), INavigation {
    val MAIN_FRAGMENT:String = FragmentListNotes.toString()
    val ADD_FRAGMENT:String = FragmentAddNote.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_activity_frame, FragmentListNotes.Create(), MAIN_FRAGMENT).commit()
    }

    override fun navigateToAddNoteFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame,FragmentAddNote.Create(), ADD_FRAGMENT).addToBackStack(ADD_FRAGMENT).commit()
    }
}