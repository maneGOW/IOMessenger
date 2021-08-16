package com.manegow.iomessenger.core

import android.app.Application
import com.manegow.iomessenger.di.AppComponent
import com.manegow.iomessenger.di.DaggerAppComponent

class IOMessengerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppComponent.factory().create(this)
    }
}
lateinit var injector: AppComponent
