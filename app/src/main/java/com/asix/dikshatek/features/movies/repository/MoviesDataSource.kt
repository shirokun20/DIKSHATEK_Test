package com.asix.dikshatek.features.movies.repository

import com.asix.dikshatek.components.config.RetrofitConfig
import com.asix.dikshatek.features.movies.model.genres.GenresResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list?language=en")
    suspend fun apiGenres(): Response<GenresResponseModel>

    @GET("discover/movie?language=en-US")
    suspend fun apiMovieByGenre(
        @Query("with_genres") genresIds: String,
        @Query("page") page: Int,
    ): Response<String>
}

object MoviesDataSource {
    val moviesDs: MovieApi by lazy {
        RetrofitConfig.retrofit.create(MovieApi::class.java)
    }
}