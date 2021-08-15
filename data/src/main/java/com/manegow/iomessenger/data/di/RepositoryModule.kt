package com.manegow.iomessenger.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.data.LocalBooksDataSource
import com.manegow.iomessenger.data.RemoteBooksDataSource
import com.manegow.iomessenger.data.messages.MessagesRepositoryImpl
import com.manegow.iomessenger.data.user.AuthRepositoryImpl
import com.manegow.iomessenger.domain.messages.repository.MessageRepository
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun bookRepositoryProvider(
        remoteBooksDataSource: RemoteBooksDataSource,
        localBooksDataSource: LocalBooksDataSource
    ) = BooksRepository(
        remoteBooksDataSource,
        localBooksDataSource
    )

    @Provides
    @Singleton
    fun firebaseFirestoreProvider() = FirebaseFirestore.getInstance()

    @Provides
    fun authRepositoryProvider(repository: AuthRepositoryImpl): AuthRepository{
        return repository
    }

    @Provides
    fun messagesRepositoryProvider(repository: MessagesRepositoryImpl): MessageRepository{
        return repository
    }
}