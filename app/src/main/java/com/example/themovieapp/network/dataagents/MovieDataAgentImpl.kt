package com.example.themovieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.network.responses.MovieListResponse
import com.example.themovieapp.utils.API_GET_NOW_PLAYING
import com.example.themovieapp.utils.BASE_URl
import com.example.themovieapp.utils.MOVIE_API_KEY
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl : MovieDataAgent {
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieTask().execute()
    }

    class GetNowPlayingMovieTask() : AsyncTask<Void,Void, MovieListResponse?>(){
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {

            val url: URL
            val reader: Reader
            val stringBuilder: StringBuilder

            try {
                url =
                    URL("""$BASE_URl$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.readTimeout = 15 * 1000

                connection.doInput = true
                connection.doOutput = false

                connection.connect()

                reader = BufferedReader(InputStreamReader(connection.inputStream))

                stringBuilder = StringBuilder()

                for (line in reader.readLines()) {
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("NowPlayigMovies", responseString)

                val movieListResponse = Gson().fromJson(
                    responseString,
                    MovieListResponse::class.java
                )
                return movieListResponse
            }
            catch (e:Exception){
                e.printStackTrace()
                Log.e("NewsError",e.message?:"")
            }
            finally {

            }
            return null
        }

    }
}