package com.manegow.iomessenger.core

import com.manegow.iomessenger.data.messages.MessagesRepositoryImpl
import com.manegow.iomessenger.data.user.AuthRepositoryImpl
import com.manegow.iomessenger.presentation.login.LoginViewModelFactory
import com.manegow.iomessenger.presentation.messages.MessagesViewModelFactory
import com.manegow.iomessenger.presentation.signup.SignupViewModelFactory
import com.manegow.iomessenger.usecases.messages.GetMessagesUseCase
import com.manegow.iomessenger.usecases.messages.SendMessageUseCase
import com.manegow.iomessenger.usecases.user.LoginUseCase
import com.manegow.iomessenger.usecases.user.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun providesSignupViewModelFactory(repository: AuthRepositoryImpl): SignupViewModelFactory {
        return SignupViewModelFactory(
            SignUpUseCase(repository)
        )
    }

    @Provides
    fun providesLoginViewModelFactory(repository: AuthRepositoryImpl): LoginViewModelFactory {
        return LoginViewModelFactory(LoginUseCase(repository))
    }

    @Provides
    fun providesMessagesViewModelFactory(repository: MessagesRepositoryImpl): MessagesViewModelFactory {
        return MessagesViewModelFactory(
            GetMessagesUseCase(repository),
            SendMessageUseCase(repository)
        )
    }
}