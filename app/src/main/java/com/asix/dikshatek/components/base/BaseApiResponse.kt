package com.asix.dikshatek.components.base

sealed class BaseApiResponse<out T> {
    data class Success<T>(val data: T) : BaseApiResponse<T>()
    data class Error(val errorMessage: String? = null) : BaseApiResponse<Nothing>()
}