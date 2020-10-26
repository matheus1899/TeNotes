package com.tenorinho.tenotes.ui

import com.tenorinho.tenotes.models.Note

interface INavigation {
    fun navigateToAddNoteFragment()
    fun navigateToShowNoteFragment(note:Note?)
    fun navigateToEditNoteFragment(note:Note?)
}