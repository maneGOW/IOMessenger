package com.manegow.iomessenger.usecases.user

import com.manegow.iomessenger.domain.user.model.User
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.usecases.utils.SingleWithParamUseCase
import io.reactivex.Single

class LoginUseCase (private val repository: AuthRepository): SingleWithParamUseCase<User, User>{
    override fun execute(t: User) = repository.login(t.username, t.password)
}