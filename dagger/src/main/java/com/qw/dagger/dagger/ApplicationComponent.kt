package com.qw.dagger.dagger

import com.qw.dagger.LoginFragment
import com.qw.dagger.dagger.module.APIModule
import com.qw.dagger.dagger.module.SubComponentsModule
import com.qw.dagger.repository.remote.api.LoginAPI
import com.qw.dagger.vm.LoginViewModel
import com.qw.dagger.vm.UserViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by qinwei on 12/29/20 5:49 PM
 * email: qinwei_it@163.com
 */
// Definition of the Application graph
//单例作用域
@Singleton
@Component(modules = [APIModule::class, SubComponentsModule::class])
interface ApplicationComponent {
    fun loginComponent(): LoginComponent.Factory

    fun loginVM(): LoginViewModel

    fun userVM(): UserViewModel

    fun loginAPI(): LoginAPI
}