package com.manegow.iomessenger

import com.manegow.iomessenger.domain.books.model.Book

interface MainActivityFragmentsListener {
    fun onSignUpClick()
    fun onLoginClick()
    fun onLoginSuccess(username: String)
    fun onSignUpSuccess(username: String)
    fun onBookClicked(book: Book)
    fun onLogoutClick()
    fun onBooksClick()
}