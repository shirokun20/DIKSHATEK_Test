package com.asix.dikshatek.features.movies.repository

import com.asix.dikshatek.components.config.RetrofitConfig
import com.asix.dikshatek.features.movies.model.genres.GenresResponseModel
import com.asix.dikshatek.features.movies.model.movieByGenre.MoviesByGenresResponseModel
import com.asix.dikshatek.features.movies.model.movieDetail.MovieDetailResponseModel
import com.asix.dikshatek.features.movies.model.reviews.ReviewsResponseModel
import com.asix.dikshatek.features.movies.model.videos.VideosResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list?language=en")
    suspend fun apiGenres(): Response<GenresResponseModel>

    @GET("discover/movie?language=en-US&sort_by=popularity.desc")
    suspend fun apiMovieByGenre(
        @Query("with_genres") with_genres: String,
        @Query("page") page: Int,
    ): Response<MoviesByGenresResponseModel>

    @GET("movie/{movie_id}")
    suspend fun apiMovieDetail(
        @Path("movie_id") movie_id: Int,
    ): Response<MovieDetailResponseModel>

    @GET("movie/{movie_id}/videos")
    suspend fun apiMovieVideos(
        @Path("movie_id") movie_id: Int,
    ): Response<VideosResponseModel>

    @GET("movie/{movie_id}/reviews?language=en-US")
    suspend fun apiMovieReviews(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int,
    ): Response<ReviewsResponseModel>
}

object MoviesDataSource {
    val moviesDs: MovieApi by lazy {
        RetrofitConfig.retrofit.create(MovieApi::class.java)
    }
}