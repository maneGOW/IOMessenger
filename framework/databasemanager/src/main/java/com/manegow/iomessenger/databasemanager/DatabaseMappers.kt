package com.manegow.iomessenger.databasemanager

import com.manegow.iomessenger.domain.Book
import java.util.*

fun List<BooksEntity>.toBookDomainList() = map(BooksEntity::toBookDomain)

fun BooksEntity.toBookDomain() = Book(
    isbn,
    title,
    author,
    description,
    genre,
    img,
    imported
)

fun Book.toBookEntity() = BooksEntity(
    1,
    isbn,
    title,
    author,
    description,
    genre,
    img,
    imported
)