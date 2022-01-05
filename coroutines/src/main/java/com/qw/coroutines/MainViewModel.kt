package com.qw.coroutines

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * create by qinwei at 2022/1/5 15:08
 */
class MainViewModel : ViewModel() {
    val repos = MediatorLiveData<List<Repo>>()
    val error = MediatorLiveData<String>()
    fun loadRepos() {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val result = request {
                retrofit.create(MainService::class.java).listRepos()
            }
            if (result.isSuccessful) {
                repos.value = result.data
            } else {
                error.value = result.message
            }
        }
    }

    suspend fun <T> request(call: suspend CoroutineScope.() -> T): ResponseResult<T> {
        var response: ResponseResult<T>
        withContext(Dispatchers.IO) {
            try {
                response = ResponseResult.success(call())
            } catch (e: Exception) {
                response = ResponseResult.fail(e)
                if (response.httpCode == 401) {
                    // TODO: 2022/1/5 跳转登录
                    cancel()
                }
            }
        }
        return response
    }

}