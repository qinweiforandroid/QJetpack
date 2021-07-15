package com.qw.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*

class MainActivity : AppCompatActivity() {
    private val liveData = MutableLiveData<String>()

    private val liveData1: LiveData<String> =
        Transformations.map(liveData) { input -> input + "_apply" }

    private val networkLiveData: MediatorLiveData<String> = MediatorLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        simple()
//        simple1()
        simple2()
    }

    private fun simple2() {
        val paramLiveData = MutableLiveData<String>()
        networkLiveData.addSource(paramLiveData, Observer {
            log("request:$it")
            object : Thread() {
                override fun run() {
                    super.run()
                    networkLiveData.postValue("response:baidu.com")
                }
            }.start()
        })
        networkLiveData.observe(this, Observer {
            log(it)
        })
        paramLiveData.value = "http://www.baidu.com"
    }



    private fun simple() {
        liveData.observe(this, Observer {
            //输出：hello liveData
            log(it)
        })
        liveData.value = "hello liveData"
    }

    private fun simple1() {
        liveData1.observe(this, Observer {
            //输出：hello liveData_apply
            log(it)
        })
        liveData.value = "hello liveData"
    }

    private fun log(msg: String) {
        Log.d("liveData", msg)
    }
}