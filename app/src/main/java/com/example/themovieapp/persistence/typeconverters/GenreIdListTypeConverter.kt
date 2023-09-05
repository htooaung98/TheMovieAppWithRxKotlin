package com.example.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.themovieapp.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdListTypeConverter {
    @TypeConverter
    fun toString(genreIdList:List<Int>?):String{
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreList(genreIdListJsonString:String):List<Int>?{
        val genreIdListType = object: TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdListJsonString,genreIdListType)
    }
}