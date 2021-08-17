package com.manegow.iomessenger.presentation.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.presentation.utils.Event
import com.manegow.iomessenger.usecases.books.GetFavBooksStatusUseCase
import com.manegow.iomessenger.usecases.books.UpdateFavBookStatusUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BookDetailViewModel(
    private val getFavBooksStatusUseCase: GetFavBooksStatusUseCase,
    private val updateFavBookStatusUseCase: UpdateFavBookStatusUseCase
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _bookValues = MutableLiveData<Book>()
    val bookValues: LiveData<Book> get() = _bookValues
    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> get() = _isFav

    private val _events = MutableLiveData<Event<BookDetailNavigation>>()
    val events: LiveData<Event<BookDetailNavigation>> get() = _events

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onBookValidation(book: Book?){
        if(book == null){
            _events.value = Event(BookDetailNavigation.CloseActivity)
            return
        }else{
            _bookValues.value = book!!
            validateFavBookStatus(book.isbn)
        }
    }

    fun onUpdateFavBookStatus(book: Book){
        disposable.add(
            updateFavBookStatusUseCase
                .invoke(book)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe{
                    isFavourite ->
                    _isFav.value = isFavourite
                }
        )
    }

    private fun validateFavBookStatus(bookIsbn: String){
        disposable.add(
            getFavBooksStatusUseCase
                .invoke(bookIsbn)
                .subscribe{
                    isFavorite ->
                    _isFav.value = isFavorite
                }
        )
    }

    sealed class BookDetailNavigation {
        object CloseActivity : BookDetailNavigation()
    }
}
