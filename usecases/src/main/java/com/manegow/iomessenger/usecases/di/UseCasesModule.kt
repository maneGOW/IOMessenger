package com.manegow.iomessenger.usecases.di

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.usecases.GetAllBooksUseCase
import com.manegow.iomessenger.usecases.GetAllFavBooksUseCase
import com.manegow.iomessenger.usecases.GetFavBooksStatusUseCase
import com.manegow.iomessenger.usecases.UpdateFavBookStatusUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getAllBooksUseCaseProvider(booksRepository: BooksRepository) =
        GetAllBooksUseCase(booksRepository)

    @Provides
    fun getAllFavBooksUseCaseProvider(booksRepository: BooksRepository) =
        GetAllFavBooksUseCase(booksRepository)

    @Provides
    fun getFavBooksStatusUseCaseProvider(booksRepository: BooksRepository) =
        GetFavBooksStatusUseCase(booksRepository)

    @Provides
    fun updateFavBooksStatusUseCaseProvider(booksRepository: BooksRepository) =
        UpdateFavBookStatusUseCase(booksRepository)
}