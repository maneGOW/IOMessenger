package com.manegow.iomessenger.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.manegow.iomessenger.R
import com.manegow.iomessenger.core.injector
import com.manegow.iomessenger.databinding.BookDetailFragmentBinding
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.imagemanager.bindCircularImageUrl
import com.manegow.iomessenger.presentation.bookdetail.BookDetailViewModel
import com.manegow.iomessenger.presentation.utils.Event
import com.manegow.iomessenger.utils.showLongToast

class BookDetailFragment : Fragment() {

    companion object {
        const val ARG_BOOK = "arg_book"

        fun newInstance(book: Book): BookDetailFragment {
            val args = Bundle()
            args.putString(ARG_BOOK, Gson().toJson(book))
            val fragment = BookDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var book: Book
    private lateinit var binding: BookDetailFragmentBinding
    private lateinit var viewModel: BookDetailViewModel
    private val factory = injector.bookDetailViewModelFactory()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)

        binding = BookDetailFragmentBinding.inflate(inflater, container,false)
        book = Gson().fromJson(requireNotNull(arguments).getString(BookDetailFragment.ARG_BOOK)!!, Book::class.java)

        binding.bookFav.setOnClickListener{
            viewModel.onUpdateFavBookStatus(book)
        }

        viewModel.bookValues.observe(viewLifecycleOwner, Observer(this::loadBook))
        viewModel.isFav.observe(viewLifecycleOwner, Observer(this::updateFavIcon))
        viewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))
        viewModel.onBookValidation(book)

        return binding.root
    }

    private fun loadBook(book:Book){
        binding.bookImageView.bindCircularImageUrl(
            url = book.img,
            placeholder = R.drawable.ic_launcher_background,
            errorPlaceholder = R.drawable.ic_launcher_foreground
        )
        binding.bookDataTitle = book.title
        binding.bookDataAuthor = book.author
        binding.bookDataDescription = book.description
    }

    private fun updateFavIcon(isFav: Boolean?){
        binding.bookFav.setImageResource(
            if(isFav != null && isFav){
                R.drawable.outline_star_24
            }else{
                R.drawable.outline_star_border_24
            }
        )
    }

    private fun validateEvents(event: Event<BookDetailViewModel.BookDetailNavigation>?){
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                BookDetailViewModel.BookDetailNavigation.CloseActivity -> {
                    requireContext().showLongToast(R.string.error_no_available_data)
                }
            }
        }
    }
}