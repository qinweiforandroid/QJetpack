package com.qw.dagger.vm

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserViewModel @Inject constructor() {

    fun loadUser(id: Int) {
        Log.d("UserViewModel", "loadUser id:${id}")
    }
}