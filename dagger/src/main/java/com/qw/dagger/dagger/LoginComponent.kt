package com.qw.dagger.dagger

import com.qw.dagger.LoginFragment
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginFragment)
}