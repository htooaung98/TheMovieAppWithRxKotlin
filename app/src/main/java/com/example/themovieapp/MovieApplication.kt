package com.example.themovieapp

import android.app.Application
import com.example.themovieapp.data.models.BaseModel
import com.example.themovieapp.data.models.MovieModelImpl

class MovieApplication:Application() {



    override fun onCreate() {
        super.onCreate()

        val movieModel = MovieModelImpl
        movieModel.initiateDatabase(this@MovieApplication)
    }
}