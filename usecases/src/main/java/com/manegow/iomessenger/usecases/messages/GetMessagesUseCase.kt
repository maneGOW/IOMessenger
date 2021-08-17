package com.manegow.iomessenger.usecases.messages

import com.manegow.iomessenger.domain.messages.model.Message
import com.manegow.iomessenger.domain.messages.repository.MessageRepository
import com.manegow.iomessenger.usecases.utils.ObservableUseCase


class GetMessagesUseCase(private val repository: MessageRepository) :
    ObservableUseCase<List<Message>> {

    override fun execute() = repository.getMessages()
}