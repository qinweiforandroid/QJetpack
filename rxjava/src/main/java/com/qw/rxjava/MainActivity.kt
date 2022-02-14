package com.qw.rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doSend()
    }

    private fun doSend() {
        Log.d(TAG, "Observable.create thread:" + Thread.currentThread().name)
        val disposable = Observable.create<Boolean> {
            it.onNext(true)
            Log.d(TAG, "发送onNext事件 thread:" + Thread.currentThread().name)
            it.onComplete()
            Log.d(TAG, "发送onComplete事件 thread:" + Thread.currentThread().name)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "接收到onNext事件 thread:" + Thread.currentThread().name)
            }, {
                Log.d(TAG, "发送onError事件 thread:" + Thread.currentThread().name)
            }, {
                Log.d(TAG, "发送onComplete事件 thread:" + Thread.currentThread().name)
            })
        Log.d(TAG, "Observable.create end thread:" + Thread.currentThread().name)
    }

    fun send(view: View) {
        doSend()
    }
}