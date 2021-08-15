package com.manegow.iomessenger.core

import android.app.Application
import com.manegow.iomessenger.data.di.RepositoryModule
import com.manegow.iomessenger.databasemanager.di.DatabaseModule
import com.manegow.iomessenger.di.AppComponent
import com.manegow.iomessenger.di.DaggerAppComponent

class IOMessengerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}

lateinit var injector: AppComponent