package com.manegow.iomessenger.data

import com.manegow.iomessenger.domain.Book
import com.manegow.iomessenger.domain.Results
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*

class BooksRepository(
    private val remoteBooksDataSource: RemoteBooksDataSource,
    private val localBooksDataSource: LocalBooksDataSource
) {
    //region public methods
    fun getAllBooks(): Single<Results> = remoteBooksDataSource.getAllBooks()
    fun getAllFavBooks(): Flowable<List<Book>> = localBooksDataSource.getAllFavBooks()
    fun getFavBookStatus(bookIsmb: String): Maybe<Boolean> =
        localBooksDataSource.getFavBookStatus(bookIsmb)

    fun updateFavBookStatus(book: Book): Maybe<Boolean> =
        localBooksDataSource.updateFavBookStatus(book)
    //endregion
}

