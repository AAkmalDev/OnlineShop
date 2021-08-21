package uz.koinot.onlineshop.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException


fun Throwable.userMessage() = when (this) {
    is ConnectException -> "Не интернет"
    is HttpException -> when (this.code()) {
        304 -> "Not Modified"
        400 -> "Bad Request"
        401 -> "Unauthorized"
        403 -> "Forbidden"
        404 -> "Not Found"
        405 -> "Method Not Allowed"
        409 -> "Драйвер не найден"
        422 -> "Unprocessable"
        in 500..600 -> "Server Error"
        else -> "Something went wrong"
    }
    is IOException -> "Network error"
    else -> "Unknown error"
}

typealias SingleBlock<T> = (T) -> Unit
