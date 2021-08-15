package com.manegow.iomessenger.databasemanager.di

import android.app.Application
import com.manegow.iomessenger.data.LocalBooksDataSource
import com.manegow.iomessenger.databasemanager.BooksRoomDataSource
import com.manegow.iomessenger.databasemanager.IOMessengerDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun databaseProvider(app: Application): IOMessengerDatabase =
        IOMessengerDatabase.getDatabase(app)

    @Provides
    fun localBooksDataSourceProvider(
        database: IOMessengerDatabase
    ): LocalBooksDataSource = BooksRoomDataSource(database)
}