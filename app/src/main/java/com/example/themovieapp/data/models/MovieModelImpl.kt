package com.example.themovieapp.data.models

import android.annotation.SuppressLint
import android.content.Context
import com.example.themovieapp.data.models.MovieModelImpl.mMovieDatabase
import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.data.vos.NOW_PLAYING
import com.example.themovieapp.data.vos.POPULAR
import com.example.themovieapp.data.vos.TOP_RATED
import com.example.themovieapp.persistence.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit

object MovieModelImpl : BaseModel(),MovieModel{

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = "NOW_PLAYING")?: listOf())

        //Network
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movieVO -> movieVO.type = NOW_PLAYING }

                mMovieDatabase?.movieDao()?.inertMovies(it.results?: listOf())

                onSuccess(it.results?: listOf())

            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)?: listOf())
        //Network
        mMovieApi.getPopularMovies(page=1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach {  movieVO -> movieVO.type = POPULAR  }

                mMovieDatabase?.movieDao()?.inertMovies(it.results?: listOf())

                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = "TOP_RATED")?: listOf())
        //Network
        mMovieApi.getTopRatedMovies(page=1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach {  movieVO -> movieVO.type = TOP_RATED  }

                mMovieDatabase?.movieDao()?.inertMovies(it.results?: listOf())

                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mMovieApi.getGenreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieByGenre(
        id: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getMoviesByGenre( id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getPopularActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?:"")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetail(id:String, onSuccess: (MovieVO) -> Unit, onFailure: (String) -> Unit) {
        //Database
        val movieFromDatabase: MovieVO? = mMovieDatabase?.movieDao()?.getMovieById(movieId = id.toInt())
        movieFromDatabase?.let {
            onSuccess(it)
        }

        //Network
        mMovieApi.getMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               val movieFromDatabaseToSync = mMovieDatabase?.movieDao()?.getMovieById(id.toInt())
                it.type = movieFromDatabaseToSync?.type

                mMovieDatabase?.movieDao()?.inertSingleMovie(it)
            },{
                onFailure(it.localizedMessage?:"")
            })
    }
    @SuppressLint("CheckResult")
    override fun getCreditsByMovie(
        id: String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditByMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       onSuccess(Pair(it.cast?:listOf(),it.crew?: listOf()))
            },{
                onFailure(it.localizedMessage?:"")
            })
    }
}