package com.qw.dagger.dagger.module

import com.qw.dagger.dagger.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class])
interface SubComponentsModule {
}