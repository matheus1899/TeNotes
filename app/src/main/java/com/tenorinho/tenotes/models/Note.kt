package com.tenorinho.tenotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable

@Entity(tableName = "_notes")
data class Note(
        @PrimaryKey(autoGenerate = true)var id:Long = 0,
        @ColumnInfo(name="_title")var title:String,
        @ColumnInfo(name="_note")var content:String="") : Serializable