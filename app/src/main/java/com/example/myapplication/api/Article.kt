package com.example.myapplication.api

data class Article(
    val authors: List<String>,
    val images: List<String>,
    val meta_description: String,
    val meta_favicon: String,
    val meta_image: String,
    val meta_keywords: List<String>,
    val meta_lang: String,
    val movies: List<Any>,
    val published: String,
    val published_guess_accuracy: String,
    val published_method_found: String,
    val source_url: String,
    val tags: List<Any>,
    val text: String,
    val text_lang: String,
    val title: String,
    val title_lang: String,
    val top_image: String
)