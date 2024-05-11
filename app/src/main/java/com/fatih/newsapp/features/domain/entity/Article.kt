package com.fatih.newsapp.features.domain.entity

data class Article(
    val source: ArticleSource,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
