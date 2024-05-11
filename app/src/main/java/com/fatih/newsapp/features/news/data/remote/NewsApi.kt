package com.fatih.newsapp.features.news.data.remote

import com.fatih.newsapp.features.news.domain.entity.News
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {
    @GET("everything")
    suspend fun getNews(@QueryMap query: HashMap<String, Any>) : News

    @GET("top-headlines")
    suspend fun getHeadlines(@QueryMap query: HashMap<String, Any>) : News
}