package ru.farkhodkhaknazarov.pickabureader.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TypeConverter {
    @TypeConverter
    fun convertImagesToString(images: List<String>): String = Gson().toJson(images)

    @TypeConverter
    fun convertStringToImages(value: String): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}