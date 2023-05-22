package com.example.themovieapp.network.dataagents

import com.example.themovieapp.data.vos.MovieVO

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}