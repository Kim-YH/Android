package com.kim.weather.base


import android.app.Application

class MyApplication : Application() {
    val mInstance : MyApplication = this

    override fun onCreate() {
        super.onCreate()
    }

    fun getInstance(): MyApplication {
        return mInstance
    }


}