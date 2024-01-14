package com.asix.dikshatek.features.movies.repository

import android.util.Log
import com.asix.dikshatek.components.base.BaseApiResponse
import com.asix.dikshatek.features.movies.model.genres.GenreModel
import com.asix.dikshatek.features.movies.model.movieByGenre.MovieModel
import com.asix.dikshatek.features.movies.model.movieDetail.MovieDetailResponseModel
import com.asix.dikshatek.features.movies.model.reviews.ReviewsResponseModel
import com.asix.dikshatek.features.movies.model.videos.VideoModel

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

    suspend fun getMoviesByGenre(with_genres: String, page: Int = 1): BaseApiResponse<List<MovieModel>> {
        return try {
            val response = client.apiMovieByGenre(with_genres = with_genres, page = page)
            Log.i("Link Api", response.raw().toString())
            if (response.isSuccessful) {
                if (response.body()!!.results.isNotEmpty()) {
                    BaseApiResponse.Success(response.body()!!.results)
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

    suspend fun getMovieDetail(movie_id: Int): BaseApiResponse<MovieDetailResponseModel> {
        return try {
            val response = client.apiMovieDetail(movie_id)
            Log.i("Link Api", response.raw().toString())
            if (response.isSuccessful) {
                BaseApiResponse.Success(response.body()!!)
            } else {
                BaseApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            BaseApiResponse.Error(e.message)
        }
    }

    suspend fun getVideos(movie_id: Int): BaseApiResponse<List<VideoModel>> {
        return try {
            val response = client.apiMovieVideos(movie_id)
            Log.i("Link Api", response.raw().toString())
            if (response.isSuccessful) {
                BaseApiResponse.Success(response.body()!!.results)
            } else {
                BaseApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            BaseApiResponse.Error(e.message)
        }
    }

    suspend fun getReviews(movie_id: Int, page: Int = 1): BaseApiResponse<ReviewsResponseModel> {
        return try {
            val response = client.apiMovieReviews(movie_id, page)
            Log.i("Link Api", response.raw().toString())
            if (response.isSuccessful) {
                BaseApiResponse.Success(response.body()!!)
            } else {
                BaseApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            BaseApiResponse.Error(e.message)
        }
    }
}