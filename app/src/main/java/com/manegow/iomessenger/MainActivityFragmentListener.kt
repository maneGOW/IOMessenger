package com.manegow.iomessenger

interface MainActivityFragmentsListener {

    fun onSignUpClick()
    fun onLoginClick()
    fun onLoginSuccess(username: String)
    fun onSignUpSuccess(username: String)
    fun onLogoutClick()
}