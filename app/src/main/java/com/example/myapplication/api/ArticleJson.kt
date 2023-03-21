package com.example.myapplication.api

data class ArticleJson(
    val nextPage: String,
    val results: List<ResultX>,
    val status: String,
    val totalResults: Int
)