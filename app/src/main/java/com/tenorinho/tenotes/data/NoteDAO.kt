package com.tenorinho.tenotes.data

//import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveData
import androidx.room.*
import com.tenorinho.tenotes.models.Note

@Dao interface NoteDAO {
    @Query("SELECT * FROM _notes")
    fun getAll():LiveData<List<Note>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(vararg notes:Note):LongArray
    @Update
    fun update(vararg notes:Note)
    @Delete
    fun delete(vararg notes:Note)
}