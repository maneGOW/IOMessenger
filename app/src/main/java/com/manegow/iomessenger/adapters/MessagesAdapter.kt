package com.manegow.iomessenger.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manegow.iomessenger.R
import com.manegow.iomessenger.domain.messages.model.Message

class MessagesAdapter(private val userName: String, private var chats: List<Message>):
RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {
    private val SENT = 0
    private val RECEIVED = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesAdapter.MessageViewHolder {
        val view = when (viewType) {
            SENT -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_sent, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_received, parent, false)
            }
        }
        return MessageViewHolder(view)
    }

    override fun getItemCount() = chats.size

    override fun onBindViewHolder(holder: MessagesAdapter.MessageViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if(chats[position].sender.contentEquals(userName)) {
            SENT
        } else {
            RECEIVED
        }
    }

    fun updateData(chats: List<Message>){
        this.chats = chats
        notifyDataSetChanged()
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val chatMessage: TextView = itemView.findViewById(R.id.chat_message)
        private val chatSender: TextView = itemView.findViewById(R.id.chat_sender)

        fun bind(chat: Message){
            chatMessage.text = chat.message
            if(!userName.contentEquals(chat.sender)){
                chatSender.text = chat.sender
            }
        }

    }
}