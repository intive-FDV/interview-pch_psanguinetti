package com.challenge.myfirstmillion.ui.model

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    class Ok<out T> : Resource<T>()
    data class Result<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val exception: Exception) : Resource<T>()
    data class Validation<out T>(val res: Int) : Resource<T>()
}