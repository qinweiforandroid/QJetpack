package com.qw.dagger.repository.remote.api

import android.util.Log

class LoginAPI {
    fun login(name: String, pwd: String): Boolean {
        Log.d("LoginAPI", "login name[${name}] pwd[${pwd}]")
        return name == "qinwei" && pwd == "123456"
    }
}