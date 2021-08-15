package com.manegow.iomessenger.usecases.messages

import com.manegow.iomessenger.domain.messages.model.Message
import com.manegow.iomessenger.domain.messages.repository.MessageRepository
import com.manegow.iomessenger.usecases.utils.CompletableWithParamUseCase

class SendMessageUseCase(private val repository: MessageRepository):
    CompletableWithParamUseCase<Message> {
    override fun execute(t: Message) = repository.sendMessage(t)
}