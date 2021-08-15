package com.manegow.iomessenger.di

import com.google.firebase.firestore.FirebaseFirestore
import com.manegow.iomessenger.data.di.RepositoryModule
import com.manegow.iomessenger.databasemanager.di.DatabaseModule
import com.manegow.iomessenger.domain.messages.repository.MessageRepository
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.requestmanager.di.APIModule
import com.manegow.iomessenger.usecases.di.UseCasesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        APIModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        UseCasesModule::class
    ]
)
interface AppComponent {

    fun inject(module: BooksListModule)
    fun inject(module: FavBooksModule)
    //fun authViewModelFactory(): AuthViewModelFactory
    //fun messagesViewModelFactory(): MessagesViewModelFactory
    fun authRepository(): AuthRepository
    fun messagesRepository(): MessageRepository
    fun firebaseFirestore(): FirebaseFirestore
}