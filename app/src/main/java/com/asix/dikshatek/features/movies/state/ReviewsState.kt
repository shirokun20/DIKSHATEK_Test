package com.asix.dikshatek.features.movies.state

import com.asix.dikshatek.features.movies.model.reviews.ReviewModel

sealed class ReviewsState {
    object Loading : ReviewsState()
    
    data class Success(val data: List<ReviewModel>, val isLoadMore: Boolean) :
        ReviewsState()

    data class Error(val errorMessage: String) : ReviewsState()
}