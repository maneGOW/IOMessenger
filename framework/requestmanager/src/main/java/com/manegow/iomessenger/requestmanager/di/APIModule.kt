package com.manegow.iomessenger.requestmanager.di

import com.manegow.iomessenger.data.RemoteBooksDataSource
import com.manegow.iomessenger.requestmanager.APIConstants.BASE_API_URL
import com.manegow.iomessenger.requestmanager.BooksRequest
import com.manegow.iomessenger.requestmanager.BooksRetrofitDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIModule{

    @Provides
    fun bookRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = BooksRequest(baseUrl)

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = BASE_API_URL

    @Provides
    fun remoteBookDataSourceProvider(
        booksRequest: BooksRequest
    ): RemoteBooksDataSource = BooksRetrofitDataSource(booksRequest)

}