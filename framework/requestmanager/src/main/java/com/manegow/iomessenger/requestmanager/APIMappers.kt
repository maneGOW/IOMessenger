package com.manegow.iomessenger.requestmanager

import com.manegow.iomessenger.domain.Book
import java.util.*

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