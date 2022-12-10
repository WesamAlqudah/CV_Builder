package com.example.cvbuilder.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
data class Certification(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val imageUrl:Int,
    val name:String,
    val year:Int
)