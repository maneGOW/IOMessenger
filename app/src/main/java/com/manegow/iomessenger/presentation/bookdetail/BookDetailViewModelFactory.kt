package com.manegow.iomessenger.presentation.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manegow.iomessenger.usecases.books.GetFavBooksStatusUseCase
import com.manegow.iomessenger.usecases.books.UpdateFavBookStatusUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class BookDetailViewModelFactory @Inject constructor(
    private val favBookStatusUseCase: GetFavBooksStatusUseCase,
    private val updateFavBookStatusUseCase: UpdateFavBookStatusUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailViewModel::class.java)) {
            return BookDetailViewModel(favBookStatusUseCase, updateFavBookStatusUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}