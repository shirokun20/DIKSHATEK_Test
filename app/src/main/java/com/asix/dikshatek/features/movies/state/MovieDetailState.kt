package com.asix.dikshatek.features.movies.state

import com.asix.dikshatek.features.movies.model.movieDetail.MovieDetailResponseModel

sealed class MovieDetailState {
    object Loading : MovieDetailState()
    data class Success(val data: MovieDetailResponseModel) : MovieDetailState()
    data class Error(val errorMessage: String) : MovieDetailState()
}