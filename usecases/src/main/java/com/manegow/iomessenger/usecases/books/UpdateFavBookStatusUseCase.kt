package com.manegow.iomessenger.usecases.books

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.books.model.Book
import io.reactivex.Maybe

class UpdateFavBookStatusUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(book: Book): Maybe<Boolean> =
        booksRepository.updateFavBookStatus(book)
}