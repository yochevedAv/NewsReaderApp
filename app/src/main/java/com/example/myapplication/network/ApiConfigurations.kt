package com.example.myapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfigurations {
    companion object {

        var retrofit: Retrofit? = null
        var base_url: String = ""


        val BASE_URL = "https://newsdata.io/api/1/"
/*
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)*/


        inline fun <reified T : Api> getClient(url: String? = null): T {
            if (retrofit == null) {
                url?.apply { base_url = this }
                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(if (base_url.endsWith("/")) base_url else "$base_url/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

            return retrofit!!.create(T::class.java) // return the APIInterface object
        }

        val client by lazy {
            OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .build()
        }

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()


    }
}

