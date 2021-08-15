package com.manegow.iomessenger.requestmanager

import com.manegow.iomessenger.data.RemoteBooksDataSource
import com.manegow.iomessenger.domain.Book
import com.manegow.iomessenger.domain.Results
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

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