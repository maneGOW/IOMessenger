package com.manegow.iomessenger.presentation.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manegow.iomessenger.usecases.messages.GetMessagesUseCase
import com.manegow.iomessenger.usecases.messages.SendMessageUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MessagesViewModelFactory @Inject constructor(
    private val getMessages: GetMessagesUseCase,
    private val sendMessages: SendMessageUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessagesViewModel::class.java)) {
            return MessagesViewModel(getMessages, sendMessages) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}