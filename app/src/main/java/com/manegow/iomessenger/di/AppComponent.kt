package com.manegow.iomessenger.di

import com.google.firebase.firestore.FirebaseFirestore
import com.manegow.iomessenger.core.ViewModelModule
import com.manegow.iomessenger.data.di.FirebaseModule
import com.manegow.iomessenger.data.di.RepositoryModule
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.presentation.login.LoginViewModelFactory
import com.manegow.iomessenger.presentation.signup.SignupViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        FirebaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun signupViewModelFactory(): SignupViewModelFactory
    fun loginViewModelFactory(): LoginViewModelFactory
    fun authRepository(): AuthRepository
    fun firebaseFirestore(): FirebaseFirestore

}