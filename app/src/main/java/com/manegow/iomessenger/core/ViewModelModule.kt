package com.manegow.iomessenger.core

import com.manegow.iomessenger.data.user.AuthRepositoryImpl
import com.manegow.iomessenger.presentation.SignupViewModelFactory
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
}