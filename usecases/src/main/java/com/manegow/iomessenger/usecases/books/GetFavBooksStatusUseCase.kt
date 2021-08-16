package com.manegow.iomessenger.usecases.books

import com.manegow.iomessenger.data.BooksRepository
import io.reactivex.Maybe

class GetFavBooksStatusUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(bookIsmb: String): Maybe<Boolean> =
        booksRepository.getFavBookStatus(bookIsmb)
}