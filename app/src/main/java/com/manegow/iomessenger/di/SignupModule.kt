package com.manegow.iomessenger.di

import com.manegow.iomessenger.presentation.signup.SignupViewModel
import com.manegow.iomessenger.usecases.user.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class SignupModule {

    @Provides
    fun signupViewModelProvider(
        signUpUseCase: SignUpUseCase
    ) = SignupViewModel(signUpUseCase)
}

@Subcomponent(modules = [(SignupModule::class)])
interface SignupComponent{
    var signupViewModel: SignupViewModel
}