package com.manegow.iomessenger.databasemanager

import com.manegow.iomessenger.domain.books.model.Book

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
    isbn.substring(0,5).toInt(),
    isbn,
    title,
    author,
    description,
    genre,
    img,
    imported
)