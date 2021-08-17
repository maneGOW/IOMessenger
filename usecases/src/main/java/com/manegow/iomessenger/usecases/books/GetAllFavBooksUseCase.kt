package com.manegow.iomessenger.usecases.books

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.books.model.Book
import io.reactivex.Flowable

class GetAllFavBooksUseCase(
    private val booksRepository: BooksRepository
){
    fun invoke(): Flowable<List<Book>> = booksRepository.getAllFavBooks()
}