package com.android.jetpack.vm

import com.android.jetpack.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: UserRepository) {
    fun login(name: String, pwd: String) {
        repository.login(name, pwd)
    }
}
