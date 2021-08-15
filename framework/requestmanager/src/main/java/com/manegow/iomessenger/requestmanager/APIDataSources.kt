package com.manegow.iomessenger.requestmanager

import com.manegow.iomessenger.data.RemoteBooksDataSource
import com.manegow.iomessenger.domain.books.model.Book
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BooksRetrofitDataSource(
    private val booksRequest: BooksRequest
): RemoteBooksDataSource {
    override fun getAllBooks(): Single<List<Book>> {
        return booksRequest
            .getService<BooksService>()
            .getAllBooks()
            .map(BooksResponseServer::toBooksDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}