package com.manegow.iomessenger.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.manegow.iomessenger.core.ViewModelModule
import com.manegow.iomessenger.data.di.FirebaseModule
import com.manegow.iomessenger.data.di.RepositoryModule
import com.manegow.iomessenger.databasemanager.di.DatabaseModule
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.presentation.bookdetail.BookDetailViewModelFactory
import com.manegow.iomessenger.presentation.booklist.BooksListViewModelFactory
import com.manegow.iomessenger.presentation.login.LoginViewModelFactory
import com.manegow.iomessenger.presentation.messages.MessagesViewModelFactory
import com.manegow.iomessenger.presentation.signup.SignupViewModelFactory
import com.manegow.iomessenger.requestmanager.di.APIModule
import com.manegow.iomessenger.usecases.di.UseCasesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        DatabaseModule::class,
        APIModule::class,
        UseCasesModule::class,
        FirebaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun signupViewModelFactory(): SignupViewModelFactory
    fun loginViewModelFactory(): LoginViewModelFactory
    fun messagesViewModelFactory(): MessagesViewModelFactory
    fun bookListViewModelFactory(): BooksListViewModelFactory
    fun bookDetailViewModelFactory(): BookDetailViewModelFactory

    fun authRepository(): AuthRepository
    fun firebaseFirestore(): FirebaseFirestore

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

}