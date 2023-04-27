package com.example.myapplication

import android.app.Application
import com.example.myapplication.module.AppModule
import com.example.myapplication.network.ApiConfigurations

//import com.example.myapplication.module.DatabaseModule
//import com.example.myapplication.module.NetworkModule
//import dagger.hilt.android.HiltAndroidApp

class MyApp:Application() {

//    val component: AppComponent by lazy {
//        DaggerAppComponent
//            .builder()
//            .appModule(AppModule(this))
//            .build()
//    }

    override fun onCreate() {
        super.onCreate()
        ApiConfigurations.base_url = BuildConfig.BASE_URL

        //component.inject(this)
    }
}

