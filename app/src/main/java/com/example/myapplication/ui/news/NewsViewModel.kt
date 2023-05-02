package com.example.myapplication.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ArticleJson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsItems = MutableLiveData<ArticleJson?>()
    val newsItems: MutableLiveData<ArticleJson?> = _newsItems

    fun loadNews() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getNews()
                if (response.isSuccessful) {
                    val newsData = response.body()
                    _newsItems.value = newsData
                } else {
                    println(response.message())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
