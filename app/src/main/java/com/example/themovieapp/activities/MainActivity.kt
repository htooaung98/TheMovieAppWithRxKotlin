package com.example.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.adapters.BannerAdapters
import com.example.themovieapp.adapters.ShowcaseAdapter
import com.example.themovieapp.dammyData.dummyGenreList
import com.example.themovieapp.data.models.MovieModel
import com.example.themovieapp.data.models.MovieModelImpl
import com.example.themovieapp.delegates.BannerViewHolderDelegate
import com.example.themovieapp.delegates.MovieViewHolderDelegate
import com.example.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.example.themovieapp.viewPods.MovieListViewPod
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , BannerViewHolderDelegate ,ShowCaseViewHolderDelegate ,MovieViewHolderDelegate{

    lateinit var mBannerAdapters: BannerAdapters
    lateinit var mShowcaseAdapter: ShowcaseAdapter

    lateinit var mBestPopularMoiveListViewPod : MovieListViewPod
    lateinit var mMoviesByGenre : MovieListViewPod

    private val mMovieModel:MovieModel=MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolBar()

        setUpViewPods()

        setUpBannerAdapter()

        setUpGenreTabLayout()

        setUpShowCaseRecyclerView()

        setUpListeners()

        requestData()
    }

    private fun setUpViewPods() {
        mBestPopularMoiveListViewPod = vpBestActorMovieList as MovieListViewPod
        mBestPopularMoiveListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenre = vpMovieListByGenre as MovieListViewPod
        mMoviesByGenre.setUpMovieListViewPod(this)
    }

    private fun setUpListeners() {
        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text?: "",Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        }
        )
    }

    private fun setUpBannerAdapter(){
        mBannerAdapters= BannerAdapters(this)
        viewPagerBanner.adapter=mBannerAdapters

        dots_indicator_Banner.attachTo(viewPagerBanner)
    }
    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private fun setUpGenreTabLayout(){
        /*dummyGenreList.forEach{
            val tab = genreTabLayout.newTab()
            tab.text=it
            genreTabLayout.addTab(tab)
        }*/

        //alternative way kotlin scope function
        dummyGenreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun requestData(){
        mMovieModel.getNowPlayingMovies(
            onSuccess ={
                mBannerAdapters.setNewData(it)
            },
            onFailure = {

            }
        )

        mMovieModel.getPopularMovies(
            onSuccess = {
                mBestPopularMoiveListViewPod.setNewData(it)
            },
            onFailure = {

            }
        )

        mMovieModel.getTopRatedMovies(
            onSuccess = {
                mShowcaseAdapter.setNewData(it)
            },
            onFailure = {

            }
        )
    }

    private fun setUpShowCaseRecyclerView(){
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowcase.adapter = mShowcaseAdapter

        rvShowcase.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover,menu)
        return true;
    }

    override fun onTapMovieFromBannerViewHolder() {
        startActivity(MovieDetailActivity.newIntent(this))
    }

    override fun onTapFromShowCaseViewHolder() {
        startActivity(MovieDetailActivity.newIntent(this))
    }

    override fun onTapMovie() {
        startActivity(MovieDetailActivity.newIntent(this))
    }


}