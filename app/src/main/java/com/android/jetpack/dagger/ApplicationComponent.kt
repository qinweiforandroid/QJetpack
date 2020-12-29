package com.android.jetpack.dagger

import com.android.jetpack.LoginActivity
import dagger.Component

/**
 * Created by qinwei on 12/29/20 5:49 PM
 * email: qinwei_it@163.com
 */
// Definition of the Application graph
@Component
interface ApplicationComponent {
    fun inject(activity: LoginActivity)
}
