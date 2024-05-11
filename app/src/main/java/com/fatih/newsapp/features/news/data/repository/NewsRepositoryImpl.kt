package com.fatih.newsapp.features.news.data.repository

import arrow.core.Either
import com.fatih.newsapp.core.exception.NetworkError
import com.fatih.newsapp.core.exception.toNetworkError
import com.fatih.newsapp.features.news.data.remote.NewsApi
import com.fatih.newsapp.features.news.domain.entity.News
import com.fatih.newsapp.features.news.domain.entity.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNews(query: HashMap<String, Any>): Either<NetworkError, News> {
        return Either.catch {
            newsApi.getNews(query = query)
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getHeadlines(query: HashMap<String, Any>): Either<NetworkError, News> {
        return Either.catch {
            newsApi.getHeadlines(query = query)
        }.mapLeft { it.toNetworkError() }
    }



}