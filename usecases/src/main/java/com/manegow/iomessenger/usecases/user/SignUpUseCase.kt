package com.manegow.iomessenger.usecases.user

import com.manegow.iomessenger.domain.user.model.User
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.usecases.utils.SingleWithParamUseCase

class SignUpUseCase (private val repository: AuthRepository): SingleWithParamUseCase<User, User> {
    override fun execute(t: User) = repository.signup(t.username, t.password)
}