package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.ResultX
import com.example.myapplication.data.Resource
import com.example.myapplication.db.ArticleDao
import kotlinx.coroutines.launch

class MyViewModel (application: Application) : AndroidViewModel(application) {


    private val repository = MyRepository(application.applicationContext)

    private val _myData = MutableLiveData<Resource<List<ResultX>>>()
    val myData: LiveData<Resource<List<ResultX>>>
        get() = _myData

    init {
        loadData()
    }

    fun loadData() {
        _myData.value = Resource.Loading
        viewModelScope.launch {
            val result = repository.getArticles()
            if (result is Resource.Success) {
                _myData.value = Resource.Success(result.data.results)
            } else if (result is Resource.Error) {
                _myData.value = Resource.Error(result.message)
            }
        }
    }
}
