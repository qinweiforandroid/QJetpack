package com.android.jetpack

import android.app.Application
import com.qw.framework.core.App


/**
 * Created by qinwei on 12/29/20 5:33 PM
 * email: qinwei_it@163.com
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        App.init(App.Builder(this))
    }
}