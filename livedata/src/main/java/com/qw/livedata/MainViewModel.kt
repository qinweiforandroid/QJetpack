package com.qw.livedata

import androidx.lifecycle.*

/**
 * Created by qinwei on 2021/7/16 9:21 下午
 * email: qinwei_it@163.com
 */
class MainViewModel : ViewModel() {
    private val englishLiveData = MutableLiveData<String>()
    val chineseData: LiveData<String> =
        Transformations.map(englishLiveData) { input -> "$input:你好" }

    fun loadChinese(english: String) {
        englishLiveData.value = english
    }

    private val userIdLivedata = MutableLiveData<Int>()

    val userLiveData: LiveData<User> =
        Transformations.switchMap(userIdLivedata) { input ->
            loadUser(input)
        }

    fun queryUser(userId: Int) {
        userIdLivedata.value = userId
    }

    private fun loadUser(userId: Int): LiveData<User> {
        return object : MutableLiveData<User>() {
            private var called = false
            override fun onActive() {
                super.onActive()
                if (!called) {
                    called = true
                    val user = User(userId, "张三", 18)
                    value = user
                }
            }
        }
    }

    //时序业务
    val userMediatorLiveData = MediatorLiveData<User>()

    fun loadUser2(userId: Int) {
        val dbSource = loadUserFromDb(userId)
        userMediatorLiveData.addSource(dbSource, Observer {
            userMediatorLiveData.removeSource(dbSource)
            if (it.id == 100) {
                userMediatorLiveData.value = it
            } else {
                val serverSource = loadUserFromServer(userId)
                userMediatorLiveData.addSource(serverSource, Observer { user ->
                    userMediatorLiveData.removeSource(serverSource)
                    userMediatorLiveData.value = user
                })
            }
        })
    }

    private fun loadUserFromDb(userId: Int): LiveData<User> {
        return object : MutableLiveData<User>() {
            private var called = false
            override fun onActive() {
                super.onActive()
                if (!called) {
                    called = true
                    val user = User(userId, "张三 From db", 18)
                    value = user
                }
            }
        }
    }

    private fun loadUserFromServer(userId: Int): LiveData<User> {
        return object : MutableLiveData<User>() {
            private var called = false
            override fun onActive() {
                super.onActive()
                if (!called) {
                    called = true
                    val user = User(userId, "张三 From Server", 18)
                    value = user
                }
            }
        }
    }

}