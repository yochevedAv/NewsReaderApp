package com.example.myapplication

import com.example.myapplication.api.ArticleJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiRequests {


    @GET("news?apikey=pub_19248f1b3480ae3dfbb139616df7eaf567f88")
    fun getNews(): Call<ArticleJson>

}