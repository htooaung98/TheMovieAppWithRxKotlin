package com.example.themovieapp.data.models

import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.network.dataagents.MovieDataAgent
import com.example.themovieapp.network.dataagents.RetrofitDataAgentImpl
import retrofit2.Retrofit

object MovieModelImpl : MovieModel{

    val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess= onSuccess,onFailure=onFailure)
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getPopularMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getPopularMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMovieGenreList(onSuccess = onSuccess,onFailure = onFailure)
    }

    override fun getMovieByGenre(
        id: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(id = id , onSuccess = onSuccess,onFailure = onFailure)
    }

    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getPopularActors(onSuccess = onSuccess,onFailure =onFailure)
    }
}