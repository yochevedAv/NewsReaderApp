package com.example.myapplication.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.api.ResultX
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsItems = MutableLiveData<List<ResultX>?>()
    val newsItems: MutableLiveData<List<ResultX>?> = _newsItems

    fun loadNews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = newsRepository.getNews()
                    if (response.isSuccessful) {
                        val newsData = response.body()
                        withContext(Dispatchers.Main) {
                            _newsItems.value = newsData
                        }
                    } else {
                        println(response.message())
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}