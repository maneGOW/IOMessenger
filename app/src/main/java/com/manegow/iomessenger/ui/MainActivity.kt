package com.manegow.iomessenger.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manegow.iomessenger.MainActivityFragmentsListener
import com.manegow.iomessenger.R

class MainActivity : AppCompatActivity(), MainActivityFragmentsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showIntroFragment()
    }

    override fun onSignUpClick() = showSignUpFragment()
    override fun onLoginClick() = showLoginFragment()
    override fun onLoginSuccess(username: String) = showChatFragment(username)
    override fun onSignUpSuccess(username: String) = showChatFragment(username)
    override fun onLogoutClick() = showSignUpFragment()

    private fun showIntroFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, IntroFragment())
            addToBackStack(null)
            commit()
        }
    }

    private fun showSignUpFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                R.animator.slide_in_from_left, R.animator.fade_out
            )
            replace(R.id.fragment_container, SignupFragment())
            addToBackStack(null)
            commit()
        }
    }

    private fun showChatFragment(userName: String) {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.animator.slide_in_from_left, R.animator.slide_out_to_left,
                R.animator.slide_in_from_left, R.animator.fade_out
            )
            replace(
                R.id.fragment_container,
                MessagesFragment.newInstance(userName),
                "MessagesFragment"
            )
            addToBackStack(null)
            commit()
        }
    }

    private fun showLoginFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left,
                R.animator.slide_in_from_left, R.animator.fade_out
            )
            replace(R.id.fragment_container, LoginFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag("MessagesFragment") != null) {
            showSignUpFragment()
            return
        }

        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}