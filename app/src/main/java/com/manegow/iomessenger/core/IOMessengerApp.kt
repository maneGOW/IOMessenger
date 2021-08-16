package com.manegow.iomessenger.core

import android.app.Application
import com.manegow.iomessenger.data.di.FirebaseModule
import com.manegow.iomessenger.data.di.RepositoryModule
import com.manegow.iomessenger.databasemanager.di.DatabaseModule
import com.manegow.iomessenger.di.AppComponent
import com.manegow.iomessenger.di.DaggerAppComponent

class IOMessengerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAppComponent.factory().create(this)

            /*
            .builder()
            .create()

            .firebaseModule(FirebaseModule())
            .repositoryModule(RepositoryModule())
            .viewModelModule(ViewModelModule())
            .build()*/
    }
}
lateinit var injector: AppComponent
