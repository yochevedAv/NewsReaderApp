package com.example.myapplication.network

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.db.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ArticleRepository
    private var readAll: LiveData<List<ArticleJson>>

    init {
        val articleDB = ArticleDatabase.getDatabase(application).localArticleDAO()
        repository = ArticleRepository(articleDB)
        readAll = repository.getAllArticles()
    }

    fun addArticles(articles: ArrayList<ArticleJson>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllArticles(articles)
        }
    }
}