package com.asix.dikshatek.features.movies.state

import com.asix.dikshatek.features.movies.model.genres.GenreModel

sealed class GenresState {
    object Loading : GenresState()
    data class Success(val data: List<GenreModel>) : GenresState()
    data class Error(val errorMessage: String) : GenresState()
}