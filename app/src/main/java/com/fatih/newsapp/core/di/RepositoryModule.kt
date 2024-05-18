package com.fatih.newsapp.core.di

import com.fatih.newsapp.features.news.data.repository.NewsRepositoryImpl
import com.fatih.newsapp.features.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl) : NewsRepository

}