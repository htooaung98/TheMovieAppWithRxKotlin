package com.example.themovieapp.network.responses

import com.example.themovieapp.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

class MovieListByGenreResponse (
    @SerializedName("items")
    val results : List<MovieVO>?
        )