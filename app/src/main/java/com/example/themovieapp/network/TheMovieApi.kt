package com.example.themovieapp.network

import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.network.responses.ActorListResponse
import com.example.themovieapp.network.responses.GenreListResponse
import com.example.themovieapp.network.responses.MovieCreditsResponse
import com.example.themovieapp.network.responses.MovieListByGenreResponse
import com.example.themovieapp.network.responses.MovieListResponse
import com.example.themovieapp.utils.API_GET_ACTORS
import com.example.themovieapp.utils.API_GET_GENRE_LIST
import com.example.themovieapp.utils.API_GET_MOVIE_BY_GENRE
import com.example.themovieapp.utils.API_GET_MOVIE_DETAIL
import com.example.themovieapp.utils.API_GET_NOW_PLAYING
import com.example.themovieapp.utils.API_GET_POPULAR
import com.example.themovieapp.utils.API_GET_TOP_RATED
import com.example.themovieapp.utils.MOVIE_API_KEY
import com.example.themovieapp.utils.PARAM_API_KEY
import com.example.themovieapp.utils.PARAM_PAGE
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int= 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR)
    fun getPopularMovies(
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int= 1,
    ):Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int= 1,
    ):Observable<MovieListResponse>


    @GET(API_GET_GENRE_LIST)
    fun getGenreList(
        @Query(PARAM_API_KEY)apiKey : String = MOVIE_API_KEY
    ):Observable<GenreListResponse>

    @GET("$API_GET_MOVIE_BY_GENRE/{id}")
    fun getMoviesByGenre(
        @Path("id")id : String,
        @Query(PARAM_API_KEY)apiKey : String = MOVIE_API_KEY
    ):Observable<MovieListByGenreResponse>

    @GET(API_GET_ACTORS)
    fun getPopularActors(
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
        @Query("page")page:Int = 1
    ):Observable<ActorListResponse>

    @GET("$API_GET_MOVIE_DETAIL/{id}")
    fun getMovieDetail(
        @Path("id")id : String,
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
    ):Observable<MovieVO>

    @GET("$API_GET_MOVIE_DETAIL/{id}/credits")
    fun getCreditByMovie(
        @Path("id")id : String,
        @Query(PARAM_API_KEY)apiKey: String = MOVIE_API_KEY,
    ):Observable<MovieCreditsResponse>
}