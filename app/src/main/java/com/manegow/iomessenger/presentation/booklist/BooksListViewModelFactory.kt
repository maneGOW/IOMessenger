package com.manegow.iomessenger.presentation.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manegow.iomessenger.usecases.books.GetAllBooksUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class BooksListViewModelFactory @Inject constructor(
    private val books: GetAllBooksUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksListViewModel::class.java)) {
            return BooksListViewModel(books) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}