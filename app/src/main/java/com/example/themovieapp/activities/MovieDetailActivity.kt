package com.example.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.models.MovieModel
import com.example.themovieapp.data.models.MovieModelImpl
import com.example.themovieapp.data.vos.MovieVO
import com.example.themovieapp.utils.IMAGE_BASE_URL
import com.example.themovieapp.viewPods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_holder_movie.view.ivMovieImage

class MovieDetailActivity : AppCompatActivity() {
    companion object{

        private const val EXTRA_MOVIE_ID = "MOVIE_ID"
        fun newIntent(context: Context,movieId:Int): Intent{
            val intent = Intent(context,MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID,movieId)
            return intent
        }
    }
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod
    private val mMovieModel: MovieModel = MovieModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)

        Toast.makeText(this,"$movieId",Toast.LENGTH_LONG).show()
        setUpViewPods()
        setUpListeners()
        requestData(movieId?:0)
    }


    private fun setUpListeners() {
        btnBack.setOnClickListener{
            super.onBackPressed()
        }
    }

    private fun setUpViewPods(){
        actorsViewPod = vpActor as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_actor), ""
        )
        creatorViewPod = vpCreators as ActorListViewPod
        creatorViewPod.setUpActorViewPod(
            R.color.colorPrimary,
            getString(R.string.lbl_creators),
            getString(R.string.lbl_more_creators)
        )
    }

    private fun requestData(movieId:Int){
        mMovieModel.getMovieDetail(
            id="$movieId",
        onSuccess = {
                    bindData(it)
        },
        onFailure = {

        })
    }

    private fun bindData(movie:MovieVO){
        tvMovieName.text = movie.title
        tvReleaseYear.text = movie.releaseDate
        tvNumberOfVotes.text = "${movie.voteCount}"
        tvRating.text = "${movie.voteAverage}"
        ratingBar.rating = movie.getRatingBasedOnFiveStars()
        tvOriginalTitle.text = movie.originalTitle
        tvPremiere.text = movie.releaseDate
        tvDescription.text = movie.overView
        Glide.with(this@MovieDetailActivity)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieImage)
        tvOverView.text = movie.overView

    }
}