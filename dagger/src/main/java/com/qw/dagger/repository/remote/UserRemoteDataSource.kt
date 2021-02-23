package com.qw.dagger.repository.remote

import com.qw.dagger.repository.remote.api.LoginAPI
import com.qw.utils.Trace
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 4:38 PM
 * email: qinwei_it@163.com
 */
class UserRemoteDataSource @Inject constructor() {
    @Inject
    lateinit var loginAPI: LoginAPI
    fun login(name: String, pwd: String): Boolean {
        Trace.d("UserRemoteDataSource:login name:$name pwd:$pwd")
        return loginAPI.login(name, pwd)
    }
}