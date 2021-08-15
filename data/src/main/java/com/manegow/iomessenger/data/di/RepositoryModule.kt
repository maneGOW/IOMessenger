package com.manegow.iomessenger.data.di

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.data.LocalBooksDataSource
import com.manegow.iomessenger.data.RemoteBooksDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun bookRepositoryProvider(
        remoteBooksDataSource: RemoteBooksDataSource,
        localBooksDataSource: LocalBooksDataSource
    ) = BooksRepository(
        remoteBooksDataSource,
        localBooksDataSource
    )
}