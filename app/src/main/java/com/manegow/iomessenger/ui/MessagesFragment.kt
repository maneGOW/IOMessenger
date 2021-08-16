package com.manegow.iomessenger.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.manegow.iomessenger.MainActivityFragmentsListener
import com.manegow.iomessenger.R
import com.manegow.iomessenger.presentation.messages.MessagesViewModel
import com.manegow.iomessenger.adapters.MessagesAdapter
import com.manegow.iomessenger.core.injector
import com.manegow.iomessenger.databinding.MessagesFragmentBinding
import com.manegow.iomessenger.domain.messages.model.Message
import com.manegow.iomessenger.utils.showLongToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.lang.ClassCastException

class MessagesFragment : Fragment() {

    companion object {
        const val ARG_USERNAME = "arg_username"

        fun newInstance(userName: String): MessagesFragment {
            val args = Bundle()
            args.putString(ARG_USERNAME, userName)
            val fragment = MessagesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val factory = injector.messagesViewModelFactory()
    private lateinit var viewModel: MessagesViewModel
    private lateinit var callback: MainActivityFragmentsListener
    private lateinit var binding: MessagesFragmentBinding
    private lateinit var userName: String
    private lateinit var adapter: MessagesAdapter
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(requireNotNull(activity), factory)
                .get(MessagesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as MainActivityFragmentsListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("El activity debe tener implementado MainActivityFragmentsListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MessagesFragmentBinding.inflate(inflater, container, false)
        userName = requireNotNull(arguments).getString(ARG_USERNAME)!!
        val manager = LinearLayoutManager(context)
        manager.reverseLayout = true
        binding.messageList.layoutManager = manager
        adapter = MessagesAdapter(userName, listOf())
        binding.messageList.adapter = adapter

        binding.logOut.setOnClickListener { callback.onLogoutClick() }

        binding.send.setOnClickListener {
            val chatMessage = Message(
                binding.message.text.toString(),
                userName,
                System.currentTimeMillis()
            )
            binding.message.setText("")
            disposables.add(
                viewModel.sendMessage(chatMessage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { Log.d("Messages Fragment", "El mensaje fue enviado") },
                        { showConnectionError() }
                    )
            )
        }

        addMessageBoxTextListener()

        disposables.add(
            viewModel.getMessages()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { adapter.updateData(it) },
                    { showConnectionError() }
                )
        )

        return binding.root
    }

    private fun showConnectionError(){
        requireContext().showLongToast(getString(R.string.connection_error))
    }

    private fun addMessageBoxTextListener(){
        binding.message.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.send.isEnabled = s.isNotEmpty()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}