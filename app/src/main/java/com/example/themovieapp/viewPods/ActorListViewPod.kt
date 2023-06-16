package com.example.themovieapp.viewPods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.adapters.ActorAdapter
import com.example.themovieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mActorAdapter: ActorAdapter
    override fun onFinishInflate() {
        setUpActorRecyclerView()

        super.onFinishInflate()
    }

    fun setNewData(actorList:List<ActorVO>){
        mActorAdapter.setNewData(actorList)
    }

    fun setUpActorViewPod(backgroundColor : Int , title: String , moreTitle :String){
        setBackgroundColor(ContextCompat.getColor(context , backgroundColor))
        tvBestActors.text=title
        tvMoreActors.text=moreTitle
        tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecyclerView(){
        mActorAdapter = ActorAdapter()
        rvBestActors.adapter=mActorAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }
}