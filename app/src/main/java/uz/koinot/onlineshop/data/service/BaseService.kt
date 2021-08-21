package uz.koinot.onlineshop.data.service

import retrofit2.HttpException
import retrofit2.Response
import uz.koinot.onlineshop.data.network.NoInternetException
import uz.koinot.onlineshop.data.network.NotFoundException
import uz.koinot.onlineshop.data.network.UnAuthorizedException
import uz.koinot.onlineshop.data.network.UnKnownException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseService {

    protected suspend fun <T : Any> createCall(call: suspend () -> Response<T>): Result<T> {

        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            t.printStackTrace()
            return Result.Error(mapToNetworkError(t))
        }

        if (response.isSuccessful) {
            if (response.body() != null) {
                return Result.Success(response.body()!!)
            }
        } else {
            val errorBody = response.errorBody()
            return if (errorBody != null) {
                Result.Error(mapApiException(response.code()))
            } else {
                Result.Error(mapToNetworkError(mapApiException(0)))
            }
        }
        return Result.Error(HttpException(response))
    }


    private fun mapApiException(code: Int): Exception {
        return when (code) {
            HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException()
            HttpURLConnection.HTTP_UNAUTHORIZED -> UnAuthorizedException()
            else -> UnKnownException()
        }
    }

    private fun mapToNetworkError(t: Throwable): Exception {
        return when (t) {
            is SocketTimeoutException -> SocketTimeoutException("Connection Timed Out")
            is UnknownHostException -> NoInternetException()
            else -> UnKnownException()

        }
    }
}