package com.manegow.iomessenger.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manegow.iomessenger.R
import com.manegow.iomessenger.databinding.ItemGridBookBinding
import com.manegow.iomessenger.domain.books.model.Book
import com.manegow.iomessenger.imagemanager.bindImageUrl
import com.manegow.iomessenger.utils.bindingInflate
import kotlinx.android.synthetic.main.item_grid_book.view.*

class BookGridAdapter(private val listener: (Book) -> Unit ): RecyclerView.Adapter<BookGridAdapter.BookGridViewHolder>() {
    private val bookList: MutableList<Book> = mutableListOf()

    fun addData(newData: List<Book>){
        bookList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookGridViewHolder(parent.bindingInflate(R.layout.item_grid_book, false), listener)

    override fun getItemCount() = bookList.size

    override fun getItemId(position: Int): Long = bookList[position].isbn.toLong()

    override fun onBindViewHolder(holder: BookGridViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    class BookGridViewHolder(private val dataBinding: ItemGridBookBinding,
                             private val listener: (Book) -> Unit): RecyclerView.ViewHolder(dataBinding.root){
        fun bind(item: Book){
            dataBinding.book = item
            itemView.book_image.bindImageUrl(
                url = item.img,
                placeholder = R.drawable.ic_launcher_background,
                errorPlaceholder = R.drawable.ic_launcher_foreground
            )
            itemView.setOnClickListener{ listener(item) }
        }
    }
}