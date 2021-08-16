package com.manegow.iomessenger.ui

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.manegow.iomessenger.MainActivityFragmentsListener
import com.manegow.iomessenger.core.injector
import com.manegow.iomessenger.databinding.SignupFragmentBinding
import com.manegow.iomessenger.presentation.SignupViewModel
import com.manegow.iomessenger.utils.InputUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.lang.ClassCastException
import androidx.lifecycle.ViewModelProviders

class SignupFragment : Fragment() {
    private val factory = injector.signupViewModelFactory()

    private lateinit var viewModel: SignupViewModel

    private lateinit var callback: MainActivityFragmentsListener

    private lateinit var binding: SignupFragmentBinding

    private val disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireNotNull(activity), factory).get(SignupViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as MainActivityFragmentsListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("El activity debe estar implementado en MainActivityFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignupFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.login.paintFlags = binding.login.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.signUp.setOnClickListener {
            if (!hasErrors()) {
                InputUtil.hideKeyboard(requireNotNull(context), view!!)
                disableSignUpButton()
                disposables.add(
                    viewModel.signUp(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                    )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ user -> callback.onSignUpSuccess(user.username) },
                            { e ->
                                enableSignUpButton()
                                showUsernameError()
                                showPasswordError()
                                Log.e("SignupFragment", "error:", e)
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

    private fun enableSignUpButton() {
        binding.signUp.isEnabled = true
    }

    private fun disableSignUpButton() {
        binding.signUp.isEnabled = false
    }
}