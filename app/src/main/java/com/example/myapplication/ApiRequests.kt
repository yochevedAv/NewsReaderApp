package com.example.myapplication

import com.example.myapplication.api.NewsJson
import com.example.myapplication.api.moviesJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiRequests {

    @Headers("X-RapidAPI-Key: 45e32fec87msh75cc4b2352277e6p1054f3jsne7b004197162",
        "X-RapidAPI-Host: moviesdatabase.p.rapidapi.com")
    @GET("/actors")
    fun getMovies(): Call<moviesJson>

    @Headers("X-RapidAPI-Key: 45e32fec87msh75cc4b2352277e6p1054f3jsne7b004197162",
        "X-RapidAPI-Host: extract-news.p.rapidapi.com")
    @GET("/v0/article")
    fun getNews(): Call<NewsJson>

}