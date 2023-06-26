package com.example.themovieapp.network.dataagents

import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.data.vos.MovieVO

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenre(
        id:String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        id:String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )
}