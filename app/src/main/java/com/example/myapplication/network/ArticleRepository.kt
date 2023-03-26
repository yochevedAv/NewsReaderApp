package com.example.myapplication.network

import androidx.lifecycle.LiveData
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.db.ILocalArticleDAO

class ArticleRepository(private val localArticleDAO: ILocalArticleDAO){

    fun insertAllArticles(articles: ArrayList<ArticleJson>) =
        localArticleDAO.insertAll(articles)

    fun getAllArticles():LiveData<List<ArticleJson>> = localArticleDAO.getAllArticles()
}