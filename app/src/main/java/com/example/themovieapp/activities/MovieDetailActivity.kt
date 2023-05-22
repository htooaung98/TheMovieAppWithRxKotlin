package com.example.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themovieapp.R
import com.example.themovieapp.viewPods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context,MovieDetailActivity::class.java)
        }
    }
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setUpViewPods()
        setUpListeners()
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
}