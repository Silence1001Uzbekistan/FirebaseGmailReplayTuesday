package com.example.firebasegmailreplaytuesday.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasegmailreplaytuesday.databinding.ItemFromBinding
import com.example.firebasegmailreplaytuesday.databinding.ItemToBinding
import com.example.firebasegmailreplaytuesday.models.Message

class MessageAdapter(var list: List<Message>, var uid: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class FromVh(var itemFromBinding: ItemFromBinding) :
        RecyclerView.ViewHolder(itemFromBinding.root) {


        fun onBind(message: Message) {

            itemFromBinding.messageTv.text = message.message
            itemFromBinding.dateTv.text = message.date

        }

    }

    inner class ToVh(var itemToBinding: ItemToBinding) :
        RecyclerView.ViewHolder(itemToBinding.root) {

        fun onBind(message: Message) {

            itemToBinding.messageTv.text = message.message
            itemToBinding.dateTv.text = message.date

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {

            return FromVh(
                ItemFromBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        } else {

            return ToVh(
                ItemToBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

    }


    override fun getItemViewType(position: Int): Int {

        return if (list[position].fromUid == uid) {
            1
        } else {
            2
        }

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == 1) {

            val fromVh = holder as FromVh
            fromVh.onBind(list[position])

        } else {

            val toVh = holder as ToVh
            toVh.onBind(list[position])

        }

    }

}