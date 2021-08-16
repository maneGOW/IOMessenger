package com.manegow.iomessenger.presentation.signup

import androidx.lifecycle.ViewModel
import com.manegow.iomessenger.domain.user.model.User
import com.manegow.iomessenger.usecases.user.SignUpUseCase

class SignupViewModel(private val singUpUseCase: SignUpUseCase) : ViewModel() {
    fun signUp(username: String, password: String) = singUpUseCase.execute(User(username, password))
}