package com.asix.dikshatek.features.movies.model.movieDetail

import com.asix.dikshatek.features.movies.model.genres.GenreModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponseModel(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdrop_path: String,
    @Json(name = "belongs_to_collection") val belongs_to_collection: MovieCollectionModel,
    val budget: Int,
    val genres: List<GenreModel>,
    val homepage: String,
    val id: Int,
    @Json(name = "imdb_id") val imdb_id: String,
    @Json(name = "original_language") val original_language: String,
    @Json(name = "original_title") val original_title: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "production_companies") val production_companies: List<ProductionCompanyModel>,
    @Json(name = "production_countries") val production_countries: List<ProductionCountryModel>,
    @Json(name = "release_date") val release_date: String,
    val revenue: Int,
    val runtime: Int,
    @Json(name = "spoken_languages") val spoken_languages: List<SpokenLanguageModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average") val vote_average: Double,
    @Json(name = "vote_count") val vote_count: Int
)

@JsonClass(generateAdapter = true)
data class MovieCollectionModel(
    val id: Int,
    val name: String,
    @Json(name = "poster_path") val poster_path: String,
    @Json(name = "backdrop_path") val backdrop_path: String
)

@JsonClass(generateAdapter = true)
data class ProductionCompanyModel(
    val id: Int,
    @Json(name = "logo_path") val logo_path: String?,
    val name: String,
    @Json(name = "origin_country") val origin_country: String
)

@JsonClass(generateAdapter = true)
data class ProductionCountryModel(
    @Json(name = "iso_3166_1") val iso_3166_1: String,
    val name: String
)

@JsonClass(generateAdapter = true)
data class SpokenLanguageModel(
    @Json(name = "english_name") val english_name: String,
    @Json(name = "iso_639_1") val iso_639_1: String,
    val name: String
)
