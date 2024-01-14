package com.asix.dikshatek.features.movies.model.reviews

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ReviewsResponseModel(
    val id: Int,
    val page: Int,
    val results: List<ReviewModel>,
    @Json(name = "total_pages") val total_pages: Int,
    @Json(name = "total_results") val total_results: Int
)

@JsonClass(generateAdapter = true)
data class ReviewModel(
    val author: String,
    @Json(name = "author_details") val author_details: AuthorDetailsModel,
    val content: String,
    @Json(name = "created_at") val createdAt: String,
    val id: String,
    @Json(name = "updated_at") val updatedAt: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class AuthorDetailsModel(
    val name: String,
    val username: String,
    @Json(name = "avatar_path") val avatar_path: String?,
    val rating: Int
)