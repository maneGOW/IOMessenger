package com.manegow.iomessenger.usecases

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.Book
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class GetAllBooksUseCase(private val booksRepository: BooksRepository){
    fun invoke(): Single<List<Book>> =
        booksRepository.getAllBooks()
}

class GetAllFavBooksUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(): Flowable<List<Book>> = booksRepository.getAllFavBooks()
}

class GetFavBooksStatusUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(bookIsmb: String): Maybe<Boolean> =
        booksRepository.getFavBookStatus(bookIsmb)
}

class UpdateFavBookStatusUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(book: Book): Maybe<Boolean> =
        booksRepository.updateFavBookStatus(book)
}