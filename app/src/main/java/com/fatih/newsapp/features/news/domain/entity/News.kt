package com.fatih.newsapp.features.news.domain.entity


data class News(
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)
