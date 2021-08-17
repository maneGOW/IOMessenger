package com.manegow.iomessenger.databasemanager

import android.content.Context
import androidx.room.*

@Database(entities = [BooksEntity::class], version = 1)
@TypeConverters(ListStringConverters::class)
abstract class IOMessengerDatabase : RoomDatabase() {

    //region Abstract methods
    abstract fun booksDao(): BooksDao
    //end region

    //region companion object
    companion object {
        private const val DATABASE_NAME = "io_messenger_db"
        @Synchronized
        fun getDatabase(context: Context): IOMessengerDatabase = Room.databaseBuilder(
            context.applicationContext,
            IOMessengerDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    //end region
}