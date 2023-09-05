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
    val profile_path:String?,

    @SerializedName("known_for_department")
    val known_for_department:String?,

    @SerializedName("original_name")
    val original_name:String?,

    @SerializedName("credit_id")
    val credit_id:String?,

    @SerializedName("department")
    val department:String?,

    @SerializedName("job")
    val job:String?,

    @SerializedName("character")
    val character:String?,

    @SerializedName("cast_id")
    val cast_id:Int?,

    @SerializedName("order")
    val order:Int?,

        )