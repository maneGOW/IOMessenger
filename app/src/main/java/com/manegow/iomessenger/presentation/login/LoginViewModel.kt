package com.manegow.iomessenger.presentation.login

import androidx.lifecycle.ViewModel
import com.manegow.iomessenger.domain.user.model.User
import com.manegow.iomessenger.usecases.user.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    fun login(username: String, password: String) = loginUseCase.execute(User(username, password))
}