package com.example.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.example.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_showcase.view.ivShowCaseMovieImage
import kotlinx.android.synthetic.main.view_holder_showcase.view.tvShowCaseMovieDate
import kotlinx.android.synthetic.main.view_holder_showcase.view.tvShowCaseMovieName

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowCaseViewHolderDelegate) : ViewHolder(itemView) {
    private var mMovie: MovieVO? = null
    init {
        itemView.setOnClickListener(){
           mMovie.let {
               mDelegate.onTapFromShowCaseViewHolder(it?.Id?:0)
           }
        }
    }

    fun bindData(movie : MovieVO){
        mMovie = movie
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
        itemView.tvShowCaseMovieName.text = movie.title
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCaseMovieImage)
    }
}