package com.manegow.iomessenger.presentation.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.presentation.utils.Event
import com.manegow.iomessenger.usecases.books.GetAllBooksUseCase
import io.reactivex.disposables.CompositeDisposable

class BooksListViewModel(private val getAllBokListUseCase: GetAllBooksUseCase) : ViewModel() {

    private val _events = MutableLiveData<Event<BooksListNavigation>>()
    val events: LiveData<Event<BooksListNavigation>> get() = _events
    private val disposable = CompositeDisposable()
    private var isLoading = false

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onRetryGetAllBooks(itemcount: Int) {
        if (itemcount > 0) {
            _events.value = Event(BooksListNavigation.HideLoading)
            return
        }
        onGetAllBooks()
    }

    fun onGetAllBooks() {
        disposable.add(
            getAllBokListUseCase
                .invoke()
                .doOnSubscribe { showLoading() }
                .subscribe({ bookList ->
                    hideLoading()
                    _events.value = Event(BooksListNavigation.ShowBookList(bookList))
                }, { error ->
                    hideLoading()
                    _events.value = Event(BooksListNavigation.ShowBookError(error))
                })
        )
    }

    private fun showLoading() {
        isLoading = true
        _events.value = Event(BooksListNavigation.ShowLoading)
    }

    private fun hideLoading(){
        isLoading = false
        _events.value = Event(BooksListNavigation.HideLoading)
    }

    sealed class BooksListNavigation {
        data class ShowBookError(val error: Throwable): BooksListNavigation()
        data class ShowBookList(val bookList: List<Book>): BooksListNavigation()
        object HideLoading : BooksListNavigation()
        object ShowLoading : BooksListNavigation()
    }
}