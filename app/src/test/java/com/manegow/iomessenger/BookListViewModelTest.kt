package com.manegow.iomessenger

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.presentation.booklist.BooksListViewModel
import com.manegow.iomessenger.presentation.utils.Event
import com.manegow.iomessenger.usecases.books.GetAllBooksUseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookListViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxScheluderRule = RxScheluderRule()

    @Mock
    lateinit var getAllBooksUseCase: GetAllBooksUseCase

    @Mock
    lateinit var eventObserver: Observer<Event<BooksListViewModel.BooksListNavigation>>

    private lateinit var bookListViewModel: BooksListViewModel

    @Before
    fun setUp() {
        bookListViewModel = BooksListViewModel(getAllBooksUseCase)
    }

    @Test
    fun `onGetAllBooks should return an expected success list of books`() {
        val expectedResult = listOf(mockedBook.copy())
        given(getAllBooksUseCase.invoke()).willReturn(Single.just(expectedResult))
        bookListViewModel.events.observeForever(eventObserver)
        bookListViewModel.onGetAllBooks()
        verify(eventObserver).onChanged(
            Event(
                BooksListViewModel.BooksListNavigation.ShowBookList(
                    expectedResult
                )
            )
        )
    }
}

val mockedBook = Book(
    "",
    "",
    "",
    "",
    "",
    "",
    false
)