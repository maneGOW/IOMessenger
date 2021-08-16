package com.manegow.iomessenger.usecases.books

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.books.model.Book
import io.reactivex.Single

class GetAllBooksUseCase(private val booksRepository: BooksRepository){
    fun invoke(): Single<List<Book>> =
        booksRepository.getAllBooks()
}