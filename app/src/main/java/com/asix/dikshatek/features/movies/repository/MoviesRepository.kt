package com.asix.dikshatek.features.movies.repository

import com.asix.dikshatek.components.base.BaseApiResponse
import com.asix.dikshatek.features.movies.model.genres.GenreModel

class MoviesRepository {
    private val client = MoviesDataSource.moviesDs

    suspend fun getGenres(): BaseApiResponse<List<GenreModel>> {
        return try {
            val response = client.apiGenres()
            if (response.isSuccessful) {
                if (response.body()!!.genres.isNotEmpty()) {
                     BaseApiResponse.Success(response.body()!!.genres)
                } else {
                    BaseApiResponse.Error("Tidak ada data")
                }
            } else {
                BaseApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            BaseApiResponse.Error(e.message)
        }
    }
}