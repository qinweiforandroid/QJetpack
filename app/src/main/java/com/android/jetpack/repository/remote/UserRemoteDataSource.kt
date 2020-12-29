package com.android.jetpack.repository.remote

import com.qw.utils.Trace
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 4:38 PM
 * email: qinwei_it@163.com
 */
class UserRemoteDataSource @Inject constructor() {
    fun login(name: String, pwd: String) {
        Trace.d("UserRemoteDataSource:login name:$name pwd:$pwd")
    }
}