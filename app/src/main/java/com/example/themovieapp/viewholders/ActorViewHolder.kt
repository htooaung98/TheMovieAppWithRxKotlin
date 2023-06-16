package com.example.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.data.vos.ActorVO
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_actor.view.ivActorImage
import kotlinx.android.synthetic.main.view_holder_actor.view.tvActorName
import kotlinx.android.synthetic.main.view_holder_movie.view.ivMovieImage
import kotlinx.android.synthetic.main.view_holder_movie.view.rbMovieRating
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieName
import kotlinx.android.synthetic.main.view_holder_movie.view.tvMovieRating

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actorVO: ActorVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actorVO.profile_path}")
            .into(itemView.ivActorImage)

        itemView.tvActorName.text = actorVO.name
    }
}