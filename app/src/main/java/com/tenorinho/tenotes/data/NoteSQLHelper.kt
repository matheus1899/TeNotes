package com.tenorinho.tenotes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteSQLHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_NAME = "TeNotes"
        val TABLE_NAME = "Notes"
        val DB_VERSION = 1

        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "_title"
        val COLUMN_CONTENT = "_content"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT NOT NULL, $COLUMN_CONTENT TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Maguila falando \"preguiça\"")
    }
}