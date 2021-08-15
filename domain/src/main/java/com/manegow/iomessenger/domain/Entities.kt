package com.manegow.iomessenger.domain

data class Results(
    val results: List<Book>
)

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val description: String,
    val genre: String,
    val img: String,
    val imported: Boolean
)