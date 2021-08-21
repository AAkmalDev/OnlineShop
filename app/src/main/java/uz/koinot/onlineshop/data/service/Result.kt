package uz.koinot.onlineshop.data.service

sealed class Result<out T: Any> {
    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()
}