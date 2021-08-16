package com.manegow.iomessenger.parcelables

import com.manegow.iomessenger.domain.books.model.Book

fun Book.toBookParcelable() = BookParcelable(
    isbn,
    title,
    img,
    description,
    author
)