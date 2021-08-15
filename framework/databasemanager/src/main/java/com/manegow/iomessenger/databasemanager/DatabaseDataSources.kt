package com.manegow.iomessenger.databasemanager

import com.manegow.iomessenger.data.LocalBooksDataSource
import com.manegow.iomessenger.domain.Book
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.Collections.emptyList

class BooksRoomDataSource(
    database: IOMessengerDatabase
) : LocalBooksDataSource {

    //region fields
    private val booksDao by lazy { database.booksDao() }
    //endregion

    //region
    override fun getAllFavBooks(): Flowable<List<Book>> = booksDao
        .getAllFavBooks()
        .map(List<BooksEntity>::toBookDomainList)
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())

    override fun getFavBookStatus(bookIsmb: String): Maybe<Boolean> {
        return booksDao.getFavBookByIsbn(bookIsmb)
            .isEmpty
            .flatMapMaybe { isEmpty ->
                Maybe.just(!isEmpty)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun updateFavBookStatus(book: Book): Maybe<Boolean> {
        val bookEntity = book.toBookEntity()
        return booksDao.getFavBookByIsbn(book.isbn)
            .isEmpty
            .flatMapMaybe { isEmpty ->
                if (isEmpty) {
                    booksDao.insertBooks(bookEntity)
                } else {
                    booksDao.deleteCharacter(bookEntity)
                }
                Maybe.just(isEmpty)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}