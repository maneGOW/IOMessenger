package com.manegow.iomessenger.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manegow.iomessenger.presentation.signup.SignupViewModel
import com.manegow.iomessenger.usecases.user.LoginUseCase
import com.manegow.iomessenger.usecases.user.SignUpUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class LoginViewModelFactory @Inject
constructor(
    private val login: LoginUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}