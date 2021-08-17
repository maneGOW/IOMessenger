package com.manegow.iomessenger.databasemanager

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListStringConverters{

    //region FIELDS
    private val gson: Gson = Gson()
    //endregion

    //region Public Methods

    @TypeConverter
    fun stringToSTringList(data: String?): List<String?>{
        if(data.isNullOrEmpty()){
            return Collections.emptyList()
        }

        return gson.fromJson(data, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun stringListToString(someObjects: List<String?>?): String?{
        return gson.toJson(someObjects)
    }

    //endregion
}