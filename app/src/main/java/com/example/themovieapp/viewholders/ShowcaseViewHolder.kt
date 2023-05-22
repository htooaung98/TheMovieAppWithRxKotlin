package com.example.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.themovieapp.delegates.ShowCaseViewHolderDelegate

class ShowcaseViewHolder(itemView: View, private val mDelegate: ShowCaseViewHolderDelegate) : ViewHolder(itemView) {
    init {
        itemView.setOnClickListener(){
            mDelegate.onTapFromShowCaseViewHolder()
        }
    }
}