package com.tenorinho.tenotes.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "_notes")
data class Note(
        @PrimaryKey(autoGenerate = true)var id:Long = 0,
        @ColumnInfo(name="_title")var title:String,
        @ColumnInfo(name="_note")var note:String="") : Serializable