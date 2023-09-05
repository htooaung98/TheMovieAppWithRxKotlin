package com.example.themovieapp.data.vos


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.themovieapp.persistence.typeconverters.GenreIdListTypeConverter
import com.example.themovieapp.persistence.typeconverters.GenreListTypeConverter
import com.example.themovieapp.persistence.typeconverters.ProductionCompanyTypeConverter
import com.example.themovieapp.persistence.typeconverters.ProductionCountryTypeConverter
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies")
@TypeConverters(
    GenreIdListTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class

)
data class MovieVO (

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds:List<Int>?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres:List<GenreVO>?,

    @SerializedName("id")
    @PrimaryKey
    val Id:Int = 0,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage:String?,

    @SerializedName("original_title")
    @ColumnInfo(name ="original_title")
    val originalTitle : String?,

    @SerializedName("overview")
    @ColumnInfo(name ="overview")
    val overView :String?,

    @SerializedName("popularity")
    @ColumnInfo(name ="popularity")
    val popularity : Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name ="poster_path")
   val posterPath : String?,

    @SerializedName("production_companies")
    @ColumnInfo(name ="production_companies")
    val production_companies:List<ProductionCompanyVO>?,

    @SerializedName("production_countries")
    @ColumnInfo(name ="production_countries")
    val production_countries:List<ProductionCountryVO>?,

    @SerializedName("release_date")
    @ColumnInfo(name ="release_date")
    val releaseDate : String?,

    @SerializedName("title")
    @ColumnInfo(name ="title")
    val title:String?,

    @SerializedName("video")
    @ColumnInfo(name ="video")
    val video:Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name ="vote_average")
    val voteAverage:Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name ="vote_count")
    val voteCount:Int?,

    @ColumnInfo(name ="type")
    var type:String?)

{

    fun getGenresAsCommaSeparatedString():String{
        return genres?.map { it.name }?.joinToString { "," }?:""
    }

    fun getProductionCountriesAsCommaSeparatedString():String{
        return production_countries?.map { it.name }?.joinToString { "," }?:""
    }
    fun getRatingBasedOnFiveStars():Float{
        return voteAverage?.div(2)?.toFloat()?:0.0f
    }

}

const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"