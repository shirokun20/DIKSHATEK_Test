package com.asix.dikshatek.features.movies.state

import com.asix.dikshatek.features.movies.model.videos.VideoModel

sealed class VideosState {
    object Loading : VideosState()
    data class Success(val data: List<VideoModel>) : VideosState()
    data class Error(val errorMessage: String) : VideosState()
}