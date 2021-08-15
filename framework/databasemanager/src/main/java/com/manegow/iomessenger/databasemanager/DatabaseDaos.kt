package com.manegow.iomessenger.databasemanager

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface BooksDao {
    @Query("select * from books")
    fun getAllFavBooks(): Flowable<List<BooksEntity>>

    @Query("select * from books where book_isbn = :isbn")
    fun getFavBookByIsbn(isbn: String): Maybe<BooksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(booksEntity: BooksEntity)

    @Delete
    fun deleteCharacter(booksEntity: BooksEntity)
}