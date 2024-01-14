package com.asix.dikshatek.features.movies.viewModel.moviesVM

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asix.dikshatek.components.base.BaseApiResponse
import com.asix.dikshatek.features.movies.model.movieByGenre.MovieModel
import com.asix.dikshatek.features.movies.repository.MoviesRepository
import com.asix.dikshatek.features.movies.state.MoviesByGenreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(genre_id: String) : ViewModel() {
    private val repository = MoviesRepository()

    private val _state = mutableStateOf<MoviesByGenreState>(MoviesByGenreState.Loading)
    private val _isRefreshing = MutableStateFlow(false)

    private var listMovies: List<MovieModel> = emptyList()

    val state: State<MoviesByGenreState> = _state
    private var gid: String = ""
    private var page: Int = 1
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        Log.i("View Model", "Fetch Movies")
        gid = genre_id
    }

    fun fetchMovies() {
        viewModelScope.launch {
            val res = repository.getMoviesByGenre(with_genres = gid, page = page)
            Log.i("Result Movies", res.toString())
            fetchMoviesResult(res)
            _isRefreshing.value = false
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        _state.value = MoviesByGenreState.Loading
        page = 1
        fetchMovies()
    }

    private fun fetchMoviesResult(result: BaseApiResponse<List<MovieModel>>) {
        when (result) {
            is BaseApiResponse.Success -> {
                val moviesData = result.data
                listMovies = if (page == 1) {
                    moviesData
                } else {
                    listMovies.plus(moviesData)
                }
                page++
                Log.i("Result Page Next", page.toString())
                _state.value = MoviesByGenreState.Success(listMovies)
            }

            is BaseApiResponse.Error -> {
                if (page == 1) {
                    _state.value = MoviesByGenreState.Error("${result.errorMessage}")
                }
            }
        }
    }

}