package com.asix.dikshatek.features.movies.state

import com.asix.dikshatek.features.movies.model.movieByGenre.MovieModel

sealed class MoviesByGenreState {
    object Loading : MoviesByGenreState()
    data class Success(val data: List<MovieModel>) : MoviesByGenreState()
    data class Error(val errorMessage: String) : MoviesByGenreState()
}