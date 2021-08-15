package com.manegow.iomessenger.requestmanager

import com.manegow.iomessenger.requestmanager.APIConstants.ENDPOINT_BOOKS
import io.reactivex.Single
import retrofit2.http.GET

interface BooksService{

    @GET(ENDPOINT_BOOKS)
    fun getAllBooks(): Single<BooksResponseServer>
}