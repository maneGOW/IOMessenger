package com.manegow.iomessenger.databasemanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class BooksEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "book_id") var id: Int,
    @ColumnInfo(name = "book_isbn") var isbn: String,
    @ColumnInfo(name = "book_title") var title: String,
    @ColumnInfo(name = "book_author") var author: String,
    @ColumnInfo(name = "book_description") var description: String,
    @ColumnInfo(name = "book_genre") var genre: String,
    @ColumnInfo(name = "book_img") var img: String,
    @ColumnInfo(name = "book_imported") var imported: Boolean
)
