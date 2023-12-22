package com.roblesdotdev.jetdo.core.di

import com.roblesdotdev.jetdo.login.domain.repository.DemoLoginRepository
import com.roblesdotdev.jetdo.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(
        loginRepository: DemoLoginRepository
    ): LoginRepository
}
