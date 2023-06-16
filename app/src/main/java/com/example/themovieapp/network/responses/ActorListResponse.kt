package com.example.themovieapp.network.responses

import com.example.themovieapp.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

class ActorListResponse (
    @SerializedName("page")
    val page:Int?,

    @SerializedName("results")
    val results:List<ActorVO>?
)