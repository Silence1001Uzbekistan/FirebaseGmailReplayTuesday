package com.example.firebasegmailreplaytuesday.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasegmailreplaytuesday.databinding.ItemUserBinding
import com.example.firebasegmailreplaytuesday.models.User
import com.squareup.picasso.Picasso

class UserAdapter(var list: List<User>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: User) {

            itemUserBinding.emailTv.text = user.email
            itemUserBinding.nameTv.text = user.display

            Picasso.get().load(user.phoneUrl).into(itemUserBinding.imageRv)

            itemUserBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(user)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position])

    }


    interface OnItemClickListener {

        fun onItemClick(user: User)

    }

}