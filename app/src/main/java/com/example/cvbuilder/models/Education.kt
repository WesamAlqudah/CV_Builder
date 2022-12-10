package com.example.cvbuilder.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Education(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val imageUrl:Int,
    val school:String,
    val degree:String
)