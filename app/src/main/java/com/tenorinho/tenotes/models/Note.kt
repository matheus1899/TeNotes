package com.tenorinho.tenotes.models

import java.io.Serializable

data class Note(var id:Long = 0, var title:String, var note:String="") : Serializable