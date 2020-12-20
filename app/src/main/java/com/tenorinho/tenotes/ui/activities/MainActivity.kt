package com.tenorinho.tenotes.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.tenotes.R
import com.tenorinho.tenotes.models.Note
import com.tenorinho.tenotes.ui.INavigation
import com.tenorinho.tenotes.ui.adapters.NoteAdapter
import com.tenorinho.tenotes.ui.fragments.FragmentAddNote
import com.tenorinho.tenotes.ui.fragments.FragmentEditNote
import com.tenorinho.tenotes.ui.fragments.FragmentListNotes
import com.tenorinho.tenotes.ui.fragments.FragmentShowNote

class MainActivity : AppCompatActivity(), INavigation {
    private val MAIN_FRAGMENT:String = FragmentListNotes::class.java.name
    private val EDIT_FRAGMENT:String = FragmentEditNote::class.java.name
    private val SHOW_FRAGMENT:String = FragmentShowNote::class.java.name
    private val ADD_FRAGMENT:String = FragmentAddNote::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init(savedInstanceState)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_note -> {
                val fragment = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)
                if(fragment != null && fragment is FragmentListNotes){
                    val adapter = this.findViewById<RecyclerView>(R.id.list_note_recyclerview).adapter as NoteAdapter
                    fragment.openEdit(adapter.pos)
                }
            }
            R.id.delete_note -> {
                val fragment = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)
                if(fragment != null && fragment is FragmentListNotes){
                    val adapter = this.findViewById<RecyclerView>(R.id.list_note_recyclerview).adapter as NoteAdapter
                    fragment.openDelete(adapter.pos)
                }
            }
        }
        return super.onContextItemSelected(item)
    }
    private fun init(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(R.id.main_activity_frame, FragmentListNotes.create(), MAIN_FRAGMENT).commit()
        }
    }
    override fun navigateToShowNoteFragment(note:Note?){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)!!)
        val fragment = FragmentShowNote.create()
        fragment.note = note
        transaction.add(R.id.main_activity_frame, fragment, SHOW_FRAGMENT)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.addToBackStack("SLA").commit()
    }
    override fun navigateToEditNoteFragment(note: Note?) {
        val transaction = supportFragmentManager.beginTransaction()
        val mainFragment: Fragment? = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)
        if(mainFragment != null) {
            val fragmentEditNote = FragmentEditNote.create()
            fragmentEditNote.note = note
            transaction.remove(mainFragment)
                .replace(R.id.main_activity_frame, fragmentEditNote, EDIT_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(EDIT_FRAGMENT).commit()
        }
        else{
            val fragmentEditNote = FragmentEditNote.create()
            fragmentEditNote.note = note
            transaction.add(R.id.main_activity_frame, fragmentEditNote, EDIT_FRAGMENT).commit()
        }
    }
    override fun navigateToAddNoteFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val mainFragment: Fragment? = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)
        if(mainFragment != null) {
            transaction.remove(mainFragment)
                .replace(R.id.main_activity_frame, FragmentAddNote.create(), ADD_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(ADD_FRAGMENT).commit()
        }
        else{
            transaction.add(R.id.main_activity_frame, FragmentAddNote.create(), ADD_FRAGMENT).commit()
        }
    }
}