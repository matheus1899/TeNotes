package com.tenorinho.tenotes.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.tenorinho.tenotes.models.Note

class NoteRepository{
    var helper:NoteSQLHelper
    constructor(context:Context){
        helper = NoteSQLHelper(context)
    }
    fun add(note:Note):Long{
        val db:SQLiteDatabase = helper.writableDatabase
        val cv = ContentValues()
        cv.put(NoteSQLHelper.COLUMN_TITLE, note.title)
        cv.put(NoteSQLHelper.COLUMN_CONTENT, note.note)

        val id:Long = db.insert(NoteSQLHelper.TABLE_NAME, null, cv)
        if(id != -1.toLong()){
            note.id = id
        }
        db.close()
        return id
    }
    fun update(note:Note):Int{
        val db:SQLiteDatabase = helper.writableDatabase
        val cv = ContentValues()
        cv.put(NoteSQLHelper.COLUMN_TITLE, note.title)
        cv.put(NoteSQLHelper.COLUMN_CONTENT, note.note)

        val lines = db.update(NoteSQLHelper.TABLE_NAME, cv, NoteSQLHelper.COLUMN_ID+" = ?", Array<String>(1){note.id.toString()})
        db.close()
        return lines
    }
    fun delete(note: Note):Int{
        val db:SQLiteDatabase = helper.writableDatabase
        val lines = db.delete(NoteSQLHelper.TABLE_NAME,NoteSQLHelper.COLUMN_ID+" = ?", Array<String>(1){note.id.toString()})
        db.close()
        return lines
    }
    fun save(note:Note){
        if(note.id == 0.toLong()){
            add(note)
        }
        else{
            update(note)
        }
    }
    fun getAll():ArrayList<Note>?{
        val db:SQLiteDatabase = helper.writableDatabase

        val sql = "SELECT * FROM ${NoteSQLHelper.TABLE_NAME} ORDER BY ${NoteSQLHelper.COLUMN_ID}"
        val args = Array<String>(0) {""}
        val cursor:Cursor? = db.rawQuery(sql, args)

        val array = ArrayList<Note>()

        if(cursor != null) {
            while(cursor.moveToNext()) {
                val id: Long = cursor.getLong(cursor.getColumnIndex(NoteSQLHelper.COLUMN_ID))
                val title: String = cursor.getString(cursor.getColumnIndex(NoteSQLHelper.COLUMN_TITLE))
                val content: String = cursor.getString(cursor.getColumnIndex(NoteSQLHelper.COLUMN_CONTENT))
                array.add(Note(id, title, content))
            }
        }
        else{
            cursor?.close()
            db.close()
            return null
        }
        cursor.close()
        db.close()
        return array
    }
}