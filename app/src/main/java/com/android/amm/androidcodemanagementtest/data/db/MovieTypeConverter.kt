package com.android.amm.androidcodemanagementtest.data.db

import androidx.room.TypeConverter
import com.android.amm.androidcodemanagementtest.models.MovieModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


open class MovieTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<MovieModel> {
        val listType = object : TypeToken<List<MovieModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<MovieModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}