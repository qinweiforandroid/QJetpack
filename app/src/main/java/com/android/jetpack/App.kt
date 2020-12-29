package com.android.jetpack

import android.app.Application
import com.android.jetpack.dagger.DaggerApplicationComponent
import com.qw.framework.App
import com.qw.framework.AppStateTracker


/**
 * Created by qinwei on 12/29/20 5:33 PM
 * email: qinwei_it@163.com
 */
class App : Application() {
    val appComponent = DaggerApplicationComponent.create()
    override fun onCreate() {
        super.onCreate()
        App.init(this, LoginActivity::class.java)
        AppStateTracker.getInstance().appState = AppStateTracker.APP_STATE_ONLINE
    }
}