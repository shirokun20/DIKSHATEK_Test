package com.asix.dikshatek.features.movies.model.genres

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresResponseModel(
    @Json(name = "genres") val genres: List<GenreModel>
)

data class GenreModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)
