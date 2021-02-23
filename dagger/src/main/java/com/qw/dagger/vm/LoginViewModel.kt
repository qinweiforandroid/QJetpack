package com.qw.dagger.vm

import com.qw.dagger.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: UserRepository) {
    fun login(name: String, pwd: String): Boolean {
        return repository.login(name, pwd)
    }
}