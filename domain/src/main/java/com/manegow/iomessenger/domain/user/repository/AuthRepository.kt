package com.manegow.iomessenger.domain.user.repository

import com.manegow.iomessenger.domain.user.model.User
import io.reactivex.Single

interface AuthRepository{
    fun signup(userName: String, password: String): Single<User>
    fun login(userName: String, password: String): Single<User>
}