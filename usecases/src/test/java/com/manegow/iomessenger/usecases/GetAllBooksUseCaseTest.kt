package com.manegow.iomessenger.usecases

import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.usecases.books.GetAllBooksUseCase
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllBooksUseCaseTest {

    @Mock
    private lateinit var booksRepository: BooksRepository

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase

    @Before
    fun seUp(){
        getAllBooksUseCase = GetAllBooksUseCase(booksRepository)
    }

    @Test
    fun `get all books use case should return a list of books`(){
        val expectedResult = listOf(mockedBook.copy())
        given(booksRepository.getAllBooks()).willReturn(Single.just(expectedResult))

        getAllBooksUseCase.invoke()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(expectedResult)
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