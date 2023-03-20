package com.example.myapplication.api

data class moviesJson(
    val entries: Int,
    val next: String,
    val page: Int,
    val results: List<Result>
)