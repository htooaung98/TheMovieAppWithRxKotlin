package com.example.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.delegates.MovieViewHolderDelegate
import com.example.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.ivMovieImage
import kotlinx.android.synthetic.main.view_holder_movie.view.rbMovieRating
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieName
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieRating

class MovieViewHolder(itemView : View, mDelegate:MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView){
    private var mMovie: MovieVO? = null
    init {
        itemView.setOnClickListener(){
            mMovie.let {
                mDelegate.onTapMovie(it?.Id?:0)
            }
        }
    }

    fun bindData(movieVO: MovieVO){
        mMovie = movieVO
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movieVO.posterPath}")
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movieVO.title
        itemView.tvMovieRating.text = movieVO.voteAverage?.toString()
        itemView.rbMovieRating.rating = movieVO.getRatingBasedOnFiveStars()
    }
}