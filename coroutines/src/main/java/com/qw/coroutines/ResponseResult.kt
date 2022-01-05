package com.qw.coroutines

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.io.Serializable

/**
 * create by qinwei at 2022/1/5 16:43
 */
class ResponseResult<T> : Serializable {
    var msg: String? = null
        private set
    var data: T? = null
        private set
    var httpCode = 0
        private set
    var message: String? = null
        private set
    val isSuccessful: Boolean
        get() = httpCode == 200

    companion object {
        fun <T> fail(e: Exception): ResponseResult<T> {
            val responseResult: ResponseResult<T> = ResponseResult()
            when (e) {
                is HttpException -> {
                    responseResult.httpCode = e.code()
                    responseResult.message = e.message()
                }
                is JsonParseException,
                is JSONException,
                -> {
                    responseResult.message = e.message
                }
                else -> {
                    responseResult.message = e.message
                }
            }
            if (BuildConfig.DEBUG) {
                responseResult.message = "网络错误,请检查网络连接"
            }
            responseResult.msg = responseResult.message
            return responseResult
        }

        fun <T> success(t: T): ResponseResult<T> {
            val responseResult: ResponseResult<T> = ResponseResult()
            responseResult.data = t
            responseResult.httpCode = 200
            return responseResult
        }
    }
}