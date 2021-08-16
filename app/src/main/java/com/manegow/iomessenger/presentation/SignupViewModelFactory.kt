package com.manegow.iomessenger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manegow.iomessenger.usecases.user.SignUpUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class SignupViewModelFactory @Inject constructor(
    private val signUp: SignUpUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(signUp) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}