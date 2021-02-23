package com.qw.dagger.repository

import com.qw.dagger.repository.local.UserLocalDataSource
import com.qw.dagger.repository.remote.UserRemoteDataSource
import com.qw.utils.Trace
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 4:26 PM
 * email: qinwei_it@163.com
 */
class UserRepository @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {
    fun login(name: String, pwd: String) {
        Trace.d("UserRepository:login name:$name pwd:$pwd")
        remoteDataSource.login(name, pwd)
    }

}