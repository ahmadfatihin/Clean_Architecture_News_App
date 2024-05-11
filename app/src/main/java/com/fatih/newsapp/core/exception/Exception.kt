package com.fatih.newsapp.core.exception

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toNetworkError() : NetworkError {
    val error = when(this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.UnknownResponse
        else -> {
            Log.e("network-error", this.stackTraceToString() )
            ApiError.UnknownError
        }
    }

    return NetworkError(
        error = error,
        t = this
    )
}