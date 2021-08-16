package com.manegow.iomessenger.requestmanager

import com.google.gson.annotations.SerializedName
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_AUTHOR
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_BOOKS
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_DESCRIPTION
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_GENRE
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_IMG
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_IMPORTED
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_ISBN
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_RESULTS
import com.manegow.iomessenger.requestmanager.APIConstants.KEY_TITLE

data class BooksResponseServer(
    @SerializedName(KEY_RESULTS) val results: ResultServer
)

data class ResultServer(
    @SerializedName(KEY_BOOKS) val books: List<BooksServer>
)

data class BooksServer(
    @SerializedName(KEY_ISBN) val isbn: String,
    @SerializedName(KEY_TITLE) val title: String,
    @SerializedName(KEY_AUTHOR) val author: String,
    @SerializedName(KEY_DESCRIPTION) val description: String,
    @SerializedName(KEY_GENRE) val genre: String,
    @SerializedName(KEY_IMG) val img: String,
    @SerializedName(KEY_IMPORTED) val imported: Boolean,
)