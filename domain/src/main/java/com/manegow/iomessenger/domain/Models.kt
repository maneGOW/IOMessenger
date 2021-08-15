package com.manegow.iomessenger.domain

data class ChatMessage(
    val id: String,
    val text: String,
    val fromId: String,
    val toId: String,
    val timestamp: Long
)

data class User(
    val uid: String,
    val username: String,
    val profileImage: String
)