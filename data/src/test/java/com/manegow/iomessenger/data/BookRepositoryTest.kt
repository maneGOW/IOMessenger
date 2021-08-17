package com.manegow.iomessenger.data

import com.manegow.iomessenger.domain.books.model.Book
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookRepositoryTest {

    @Mock
    private lateinit var localBooksDataSource: LocalBooksDataSource

    @Mock
    private lateinit var remoteBooksDataSource: RemoteBooksDataSource

    private lateinit var booksRepository: BooksRepository

    @Before
    fun setUp(){
        booksRepository = BooksRepository(remoteBooksDataSource, localBooksDataSource)
    }

    @Test
    fun `getAllbooks should return an expected list of books from server`(){
        val expectedResult = listOf(mockedBook.copy())
        given(remoteBooksDataSource.getAllBooks()).willReturn(Single.just(expectedResult))

        booksRepository.getAllBooks()
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