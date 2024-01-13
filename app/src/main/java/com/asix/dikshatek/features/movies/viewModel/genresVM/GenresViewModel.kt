package com.asix.dikshatek.features.movies.viewModel.genresVM

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asix.dikshatek.components.base.BaseApiResponse
import com.asix.dikshatek.features.movies.model.genres.GenreModel
import com.asix.dikshatek.features.movies.repository.MoviesRepository
import com.asix.dikshatek.features.movies.state.GenresState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class GenresViewModel : ViewModel() {
    private val repository = MoviesRepository()
    private val _state = mutableStateOf<GenresState>(GenresState.Loading)
    private val _isRefreshing = MutableStateFlow(false)
    val state: State<GenresState> = _state
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        Log.i("View Model", "Fetch Genres")
        fetchGenres()
    }

    fun fetchGenres() {
        viewModelScope.launch {
            val res = repository.getGenres()
            _fetchGenres(res)
            _isRefreshing.value = false
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        _state.value = GenresState.Loading
        fetchGenres()
    }

    private fun _fetchGenres(result: BaseApiResponse<List<GenreModel>>) {
        when (result) {
            is BaseApiResponse.Success -> {
                var genres = result.data
                Log.i("Jumlah Genres", genres.size.toString())
                _state.value = GenresState.Success(genres)
            }

            is BaseApiResponse.Error -> {
                _state.value = GenresState.Error("${result.errorMessage}")
            }
        }
    }
}