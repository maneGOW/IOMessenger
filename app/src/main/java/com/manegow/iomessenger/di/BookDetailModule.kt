package com.manegow.iomessenger.di

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.presentation.bookdetail.BookDetailViewModelFactory
import com.manegow.iomessenger.usecases.books.GetFavBooksStatusUseCase
import com.manegow.iomessenger.usecases.books.UpdateFavBookStatusUseCase
import dagger.Module
import dagger.Provides

@Module
class BookDetailModule(private val book: Book?) {
    @Provides
    fun provideBookDetailViewModelFactory(repository: BooksRepository): BookDetailViewModelFactory {
        return BookDetailViewModelFactory(
            GetFavBooksStatusUseCase(repository),
            UpdateFavBookStatusUseCase(repository)
        )
    }



}