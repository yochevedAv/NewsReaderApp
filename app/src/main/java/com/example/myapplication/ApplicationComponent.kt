package com.example.myapplication


import com.example.myapplication.module.NetworkModule
import com.example.myapplication.ui.news.NewsFragment
import com.example.myapplication.ui.news.NewsService
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: MyApp)
    fun inject(fragment: NewsFragment)

    fun retrofit(): Retrofit
    fun newsService(): NewsService
}