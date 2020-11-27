package com.android.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qw.framework.App
import com.qw.framework.AppStateTracker
import com.qw.framework.ui.QFragmentActivity
import com.qw.framework.ui.SupportFragmentListActivity
import com.qw.jetpack.workmanger.WorkManagerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        AppStateTracker.init(application)
        AppStateTracker.getInstance().appState = AppStateTracker.APP_STATE_ONLINE
        App.init(application, MainActivity::class.java)
        val cs = ArrayList<QFragmentActivity.Clazz>()
        cs.add(QFragmentActivity.Clazz("WorkManager", WorkManagerFragment::class.java))
        SupportFragmentListActivity.startActivity(this, cs, false)
        finish()
    }
}