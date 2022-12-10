package com.example.cvbuilder.database

import androidx.room.TypeConverter
import com.example.cvbuilder.models.Certification
import com.example.cvbuilder.models.Education
import com.example.cvbuilder.models.Experience
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {
    @TypeConverter
    fun fromListExp(value : List<Experience>) = Json.encodeToString(value)
    @TypeConverter
    fun toListExp(value: String) = Json.decodeFromString<List<Experience>>(value)

    @TypeConverter
    fun fromListEdu(value : List<Education>) = Json.encodeToString(value)
    @TypeConverter
    fun toListEdu(value: String) = Json.decodeFromString<List<Education>>(value)

    @TypeConverter
    fun fromListCer(value : List<Certification>) = Json.encodeToString(value)
    @TypeConverter
    fun toListCer(value: String) = Json.decodeFromString<List<Certification>>(value)

    @TypeConverter
    fun fromMap(value : Map<String,List<String>>) = Json.encodeToString(value)
    @TypeConverter
    fun toMap(value: String) = Json.decodeFromString<Map<String,List<String>>>(value)



}