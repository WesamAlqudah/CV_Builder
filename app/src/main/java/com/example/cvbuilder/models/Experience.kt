package com.example.cvbuilder.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val imageUrl:Int,
    val jobTitle:String,
    val company:String,
    val date:String,
    val location:String,
    val description:String
)