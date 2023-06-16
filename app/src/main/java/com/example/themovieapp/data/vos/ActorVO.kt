package com.example.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

class ActorVO (
    @SerializedName("adult")
    val adult:Boolean?,

    @SerializedName("gender")
    val gender:Int?,

    @SerializedName("id")
    val id:Int?,

    @SerializedName("popularity")
    val popularity:Double?,

    @SerializedName("name")
    val name:String?,

    @SerializedName("profile_path")
    val profile_path:String?
        )