package com.manegow.iomessenger.presentation.messages

import androidx.lifecycle.ViewModel
import com.manegow.iomessenger.domain.messages.model.Message
import com.manegow.iomessenger.usecases.messages.GetMessagesUseCase
import com.manegow.iomessenger.usecases.messages.SendMessageUseCase

class MessagesViewModel(
    private val getMessages: GetMessagesUseCase,
    private val sendMessage: SendMessageUseCase
) : ViewModel() {
    fun sendMessage(message: Message) = sendMessage.execute(message)
    fun getMessages() = getMessages.execute()
}