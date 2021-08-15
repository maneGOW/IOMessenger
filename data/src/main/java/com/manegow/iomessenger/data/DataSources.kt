package com.manegow.iomessenger.data

import com.manegow.iomessenger.domain.Book
import com.manegow.iomessenger.domain.Results
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

interface RemoteBooksDataSource {
    fun getAllBooks(): Single<List<Book>>
}

interface LocalBooksDataSource {
    fun getAllFavBooks(): Flowable<List<Book>>
    fun getFavBookStatus(bookIsmb: String): Maybe<Boolean>
    fun updateFavBookStatus(book: Book): Maybe<Boolean>
}