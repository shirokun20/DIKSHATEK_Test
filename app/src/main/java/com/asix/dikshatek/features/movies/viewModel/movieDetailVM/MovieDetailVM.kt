package com.asix.dikshatek.features.movies.viewModel.movieDetailVM

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asix.dikshatek.components.base.BaseApiResponse
import com.asix.dikshatek.features.movies.model.movieDetail.MovieDetailResponseModel
import com.asix.dikshatek.features.movies.model.reviews.ReviewModel
import com.asix.dikshatek.features.movies.model.reviews.ReviewsResponseModel
import com.asix.dikshatek.features.movies.model.videos.VideoModel
import com.asix.dikshatek.features.movies.repository.MoviesRepository
import com.asix.dikshatek.features.movies.state.MovieDetailState
import com.asix.dikshatek.features.movies.state.ReviewsState
import com.asix.dikshatek.features.movies.state.VideosState
import kotlinx.coroutines.launch

class MovieDetailViewModel(id: String) : ViewModel() {
    private val _repository = MoviesRepository()
    private val _stateDs = mutableStateOf<MovieDetailState>(MovieDetailState.Loading)
    private val _stateVds = mutableStateOf<VideosState>(VideosState.Loading)
    private val _stateRvs = mutableStateOf<ReviewsState>(ReviewsState.Loading)
    private var listReview: List<ReviewModel> = emptyList()
    val stateDs: State<MovieDetailState> = _stateDs
    val stateVds: State<VideosState> = _stateVds
    val stateRvs: State<ReviewsState> = _stateRvs
    private var movieid: Int = 0
    private var currentPage: Int = 1

    init {
        Log.i("View Model", "Fetch All")
        movieid = id.toInt()
    }

    fun fetchAll() {
        fetchDetailMovie()
        fetchVideos()
        fetchReviews()
    }

    private fun fetchDetailMovie() {
        viewModelScope.launch {
            val res = _repository.getMovieDetail(movieid)
            fetchDetailMovieResult(res)
        }
    }

    private fun fetchVideos() {
        viewModelScope.launch {
            val res = _repository.getVideos(movieid)
            fetchVideosResult(res)
        }
    }

    fun fetchReviews() {
        viewModelScope.launch {
            val res = _repository.getReviews(movieid, page = currentPage)
            fetchReviewsResult(res)
        }
    }

    private fun fetchDetailMovieResult(result: BaseApiResponse<MovieDetailResponseModel>) {
        when (result) {
            is BaseApiResponse.Success -> {
                val response = result.data
                _stateDs.value = MovieDetailState.Success(response)
            }

            is BaseApiResponse.Error -> {
                _stateDs.value = MovieDetailState.Error("${result.errorMessage}")
            }
        }
    }

    private fun fetchVideosResult(result: BaseApiResponse<List<VideoModel>>) {
        when (result) {
            is BaseApiResponse.Success -> {
                val response = result.data
                _stateVds.value = VideosState.Success(response)
            }

            is BaseApiResponse.Error -> {
                _stateVds.value = VideosState.Error("${result.errorMessage}")
            }
        }
    }

    private fun fetchReviewsResult(result: BaseApiResponse<ReviewsResponseModel>) {
        when (result) {
            is BaseApiResponse.Success -> {
                val response = result.data
                listReview = if (currentPage == 1) {
                    response.results
                } else {
                    listReview.plus(response.results)
                }
                _stateRvs.value = ReviewsState.Success(
                    data = listReview,
                    isLoadMore = !(currentPage >= response.total_pages || listReview.isEmpty())
                )
                currentPage++
            }

            is BaseApiResponse.Error -> {
                _stateRvs.value = ReviewsState.Error("${result.errorMessage}")
            }
        }
    }
}