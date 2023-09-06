package com.example.themovieapp.data.models

import android.content.Context
import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.data.vos.NOW_PLAYING
import com.example.themovieapp.data.vos.POPULAR
import com.example.themovieapp.data.vos.TOP_RATED
import com.example.themovieapp.network.dataagents.MovieDataAgent
import com.example.themovieapp.network.dataagents.RetrofitDataAgentImpl
import com.example.themovieapp.persistence.MovieDatabase
import retrofit2.Retrofit

object MovieModelImpl : MovieModel{

    val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    private var mMovieDatabase:MovieDatabase? = null

    fun initDatabase(context:Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = "NOW_PLAYING")?: listOf())

        //Network
        mMovieDataAgent.getNowPlayingMovies(onSuccess= {
        it.forEach{ movie -> movie.type = NOW_PLAYING }
            mMovieDatabase?.movieDao()?.inertMovies(it)

            onSuccess(it)
        },
            onFailure=onFailure)
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)?: listOf())
        //Network
        mMovieDataAgent.getPopularMovies(onSuccess = {
        it.forEach{movie -> movie.type = POPULAR}

            mMovieDatabase?.movieDao()?.inertMovies(it)

            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = "TOP_RATED")?: listOf())
        //Network
        mMovieDataAgent.getPopularMovies(onSuccess = {
            it.forEach{movie -> movie.type = TOP_RATED}

            mMovieDatabase?.movieDao()?.inertMovies(it)

            onSuccess(it)
        }, onFailure = onFailure)
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

    override fun getMovieDetail(id:String,onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit) {
        //Database
        val movieFromDatabase: MovieVO? = mMovieDatabase?.movieDao()?.getMovieById(movieId = id.toInt())
        movieFromDatabase?.let {
            onSuccess(it)
        }

        mMovieDataAgent.getMovieDetail(id = id,onSuccess = {
            val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = id.toInt())
            it.type = movieFromDatabase?.type

            mMovieDatabase?.movieDao()?.inertSingleMovie(it)

            onSuccess(it)
        },onFailure=onFailure)
    }
    override fun getCreditsByMovie(
        id: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditByMovie(id = id,onSuccess = onSuccess, onFailure = onFailure)
    }
}