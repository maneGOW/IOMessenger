package com.manegow.iomessenger.domain.messages.repository

import com.manegow.iomessenger.domain.messages.model.Message
import io.reactivex.Completable
import io.reactivex.Observable

interface MessageRepository {
    fun sendMessage(message: Message): Completable
    fun getMessages(): Observable<List<Message>>
}