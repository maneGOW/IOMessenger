package com.manegow.iomessenger.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.manegow.iomessenger.MainActivityFragmentsListener
import com.manegow.iomessenger.presentation.login.LoginViewModel
import com.manegow.iomessenger.R
import com.manegow.iomessenger.core.injector
import com.manegow.iomessenger.databinding.LoginFragmentBinding
import com.manegow.iomessenger.utils.InputUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.lang.ClassCastException
import java.lang.Exception

class LoginFragment : Fragment() {

    private val factory = injector.loginViewModelFactory()
    private lateinit var viewModel: LoginViewModel
    private lateinit var callback: MainActivityFragmentsListener
    private lateinit var binding: LoginFragmentBinding
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            if (!hasErrors()) {
                InputUtil.hideKeyboard(requireNotNull(context), view!!)
                disableLoginButton()
                disposables.add(
                    viewModel.login(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { user -> callback.onLoginSuccess(user.username) },
                            { e ->
                                enableLoginButton()
                                showUsernameError()
                                showPasswordError()
                                Log.e("LoginFragment", "Error: ", e)
                            }
                        )
                )
            }
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(requireNotNull(activity), factory).get(LoginViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as MainActivityFragmentsListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("El activity debe implementar MainActivityFragmentListener")
        }
    }

    private fun hasErrors(): Boolean {
        var hasError = false

        val usernameValue = binding.username.text.toString()
        if (usernameValue.isEmpty() || usernameValue.length < 8) {
            hasError = true
            showUsernameError()
        } else {
            hideUsernameError()
        }

        val passwordValue = binding.password.text.toString()
        if (passwordValue.isEmpty() || passwordValue.length < 8) {
            hasError = true
            showPasswordError()
        } else {
            hidePasswordError()
        }

        return hasError
    }

    private fun showUsernameError() {
        binding.usernameError.visibility = View.VISIBLE
    }

    private fun hideUsernameError() {
        binding.usernameError.visibility = View.GONE
    }

    private fun showPasswordError() {
        binding.passwordError.visibility = View.VISIBLE
    }

    private fun hidePasswordError() {
        binding.passwordError.visibility = View.GONE
    }

    private fun enableLoginButton() {
        binding.login.isEnabled = true
    }

    private fun disableLoginButton() {
        binding.login.isEnabled = false
    }
}