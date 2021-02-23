package com.qw.dagger.dagger.module

import com.qw.dagger.repository.remote.api.LoginAPI
import dagger.Module
import dagger.Provides

@Module
class APIModule {

    @Provides
    fun provideLoginAPI(): LoginAPI {
        //有开发者控制创建对象的流程
        return LoginAPI()
    }
}