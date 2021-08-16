package com.manegow.iomessenger.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manegow.iomessenger.MainActivityFragmentsListener
import com.manegow.iomessenger.R
import com.manegow.iomessenger.adapters.BookGridAdapter
import com.manegow.iomessenger.core.injector
import com.manegow.iomessenger.databinding.BooksListFragmentBinding
import com.manegow.iomessenger.presentation.booklist.BooksListViewModel
import com.manegow.iomessenger.presentation.utils.Event
import com.manegow.iomessenger.utils.setItemDecorationSpacing
import com.manegow.iomessenger.utils.showLongToast
import java.lang.ClassCastException

class BooksListFragment : Fragment() {

    private val factory = injector.bookListViewModelFactory()
    private lateinit var viewModel: BooksListViewModel
    private lateinit var binding: BooksListFragmentBinding
    private lateinit var callback: MainActivityFragmentsListener
    private lateinit var bookGridAdapter: BookGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(requireNotNull(activity), factory).get(BooksListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callback = context as MainActivityFragmentsListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("El activity debe implementar MainActivityFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BooksListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookGridAdapter = BookGridAdapter { book ->
            callback.onBookClicked(book)
        }

        binding.rvCharacterList.run {
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = bookGridAdapter
        }

        binding.srwCharacterList.setOnRefreshListener {
            viewModel.onRetryGetAllBooks(binding.rvCharacterList.adapter?.itemCount ?: 0)
        }

        viewModel.events.observe(viewLifecycleOwner, Observer( this::validateEvents ))

        viewModel.onGetAllBooks()
    }

    private fun validateEvents(event: Event<BooksListViewModel.BooksListNavigation>?){
        event?.getContentIfNotHandled()?.let { navigation ->
            when(navigation){
                is BooksListViewModel.BooksListNavigation.ShowBookError -> navigation.run{
                    requireContext().showLongToast("Error -> ${error.message}")
                }
                is BooksListViewModel.BooksListNavigation.ShowBookList -> navigation.run{
                    bookGridAdapter.addData(bookList)
                }
                BooksListViewModel.BooksListNavigation.HideLoading ->{
                    binding.srwCharacterList.isRefreshing = false
                }
                BooksListViewModel.BooksListNavigation.ShowLoading ->{
                    binding.srwCharacterList.isRefreshing = true
                }
            }
        }
    }

}