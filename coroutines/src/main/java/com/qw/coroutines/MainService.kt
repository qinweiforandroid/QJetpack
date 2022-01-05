package com.qw.coroutines

import retrofit2.http.GET
import retrofit2.http.Path


/**
 * create by qinwei at 2022/1/5 15:08
 */
interface MainService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String = "qinweiforandroid"): List<Repo>
}