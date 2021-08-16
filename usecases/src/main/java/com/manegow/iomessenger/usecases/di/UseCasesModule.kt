package com.manegow.iomessenger.usecases.di
import com.manegow.iomessenger.data.BooksRepository
import com.manegow.iomessenger.domain.messages.repository.MessageRepository
import com.manegow.iomessenger.domain.user.repository.AuthRepository
import com.manegow.iomessenger.usecases.books.GetAllBooksUseCase
import com.manegow.iomessenger.usecases.books.GetAllFavBooksUseCase
import com.manegow.iomessenger.usecases.books.GetFavBooksStatusUseCase
import com.manegow.iomessenger.usecases.books.UpdateFavBookStatusUseCase
import com.manegow.iomessenger.usecases.messages.GetMessagesUseCase
import com.manegow.iomessenger.usecases.messages.SendMessageUseCase
import com.manegow.iomessenger.usecases.user.LoginUseCase
import com.manegow.iomessenger.usecases.user.SignUpUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getAllBooksUseCaseProvider(booksRepository: BooksRepository) =
        GetAllBooksUseCase(booksRepository)

    @Provides
    fun getAllFavBooksUseCaseProvider(booksRepository: BooksRepository) =
        GetAllFavBooksUseCase(booksRepository)

    @Provides
    fun getFavBooksStatusUseCaseProvider(booksRepository: BooksRepository) =
        GetFavBooksStatusUseCase(booksRepository)

    @Provides
    fun updateFavBooksStatusUseCaseProvider(booksRepository: BooksRepository) =
        UpdateFavBookStatusUseCase(booksRepository)

    @Provides
    fun getMessagesUseCase(messageRepository: MessageRepository) =
        GetMessagesUseCase(messageRepository)

    @Provides
    fun sendMessageUseCase(messageRepository: MessageRepository) =
        SendMessageUseCase(messageRepository)

    @Provides
    fun loginUseCase(authRepository: AuthRepository) =
        LoginUseCase(authRepository)

    @Provides
    fun singupUseCase(authRepository: AuthRepository) =
        SignUpUseCase(authRepository)
}