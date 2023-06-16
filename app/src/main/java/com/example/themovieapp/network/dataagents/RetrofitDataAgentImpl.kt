package com.example.themovieapp.network.dataagents

import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.GenreVO
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.network.TheMovieApi
import com.example.themovieapp.network.responses.ActorListResponse
import com.example.themovieapp.network.responses.GenreListResponse
import com.example.themovieapp.network.responses.MovieListByGenreResponse
import com.example.themovieapp.network.responses.MovieListResponse
import com.example.themovieapp.utils.BASE_URl
import com.example.themovieapp.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl: MovieDataAgent{

    private var mTheMoviepi: TheMovieApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15,TimeUnit.SECONDS)
            .readTimeout(15,TimeUnit.SECONDS)
            .writeTimeout(15,TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URl)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMoviepi = retrofit.create(TheMovieApi::class.java)
    }
    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        mTheMoviepi?.getNowPlayingMovies()?.enqueue(object :Callback<MovieListResponse>{
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    val movieList = response.body()?.results?: listOf()
                    onSuccess(movieList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMoviepi?.getPopularMovies()?.enqueue(object : Callback<MovieListResponse>{
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    val movieList = response.body()?.results?: listOf()
                    onSuccess(movieList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMoviepi?.getTopRatedMovies()?.enqueue(object : Callback<MovieListResponse>{
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    val movieList = response.body()?.results?: listOf()
                    onSuccess(movieList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getMovieGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMoviepi?.getGenreList()?.enqueue(object : Callback<GenreListResponse>{
            override fun onResponse(
                call: Call<GenreListResponse>,
                response: Response<GenreListResponse>
            ) {
                if(response.isSuccessful){
                    val genreList = response.body()?.results?: listOf()
                    onSuccess(genreList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<GenreListResponse>, t: Throwable) {
               onFailure(t.message?:"")
            }

        })
    }

    override fun getMoviesByGenre(id: String,onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMoviepi?.getMoviesByGenre(id = id)?.enqueue(object : Callback<MovieListByGenreResponse>{
            override fun onResponse(
                call: Call<MovieListByGenreResponse>,
                response: Response<MovieListByGenreResponse>
            ) {
                if(response.isSuccessful){
                    val movieList = response?.body()?.results?: listOf()
                    onSuccess(movieList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieListByGenreResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getPopularActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMoviepi?.getPopularActors()?.enqueue(object : Callback<ActorListResponse>{
            override fun onResponse(
                call: Call<ActorListResponse>,
                response: Response<ActorListResponse>
            ) {
                if(response.isSuccessful){
                    val actorList = response.body()?.results?: listOf()
                    onSuccess(actorList)
                }
                else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<ActorListResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }
}