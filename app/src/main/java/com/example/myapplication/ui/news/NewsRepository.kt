package com.example.myapplication.ui.news

import com.example.myapplication.api.ArticleJson
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews(): Response<ArticleJson> {
        return newsService.getNews()
    }
}

