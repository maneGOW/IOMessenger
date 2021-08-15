package com.manegow.iomessenger.requestmanager

import com.manegow.iomessenger.domain.books.model.Book

fun BooksResponseServer.toBooksDomainList(): List<Book> = results.books.map { it ->
    it.run {
        Book(
            isbn,
            title,
            author,
            description,
            genre,
            img,
            imported
        )
    }
}