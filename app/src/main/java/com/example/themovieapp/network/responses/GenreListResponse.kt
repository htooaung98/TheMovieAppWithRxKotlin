package com.example.themovieapp.network.responses

import com.example.themovieapp.data.vos.GenreVO
import com.google.gson.annotations.SerializedName

class GenreListResponse (
    @SerializedName("genres")
    val results:List<GenreVO>?
        )