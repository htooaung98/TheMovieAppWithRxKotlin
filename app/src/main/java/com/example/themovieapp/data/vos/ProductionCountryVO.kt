package com.example.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

class ProductionCountryVO(
    @SerializedName("iso_3166_1")
    val iso_3166_1:String,

    @SerializedName("name")
    val name:String
)