package com.fatih.newsapp.features.news.domain.repository

import arrow.core.Either
import com.fatih.newsapp.core.exception.NetworkError
import com.fatih.newsapp.features.news.domain.entity.News

interface NewsRepository {

    suspend fun getNews(query: HashMap<String, Any>) : Either<NetworkError, News>

    suspend fun getHeadlines(query: HashMap<String, Any>) : Either<NetworkError, News>
}