package com.example.rentateamtestapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rentateamtestapp.R
import com.example.rentateamtestapp.data.User
import com.example.rentateamtestapp.extensions.inflate
import kotlinx.android.synthetic.main.users_list_item.view.*

class UsersAdapter(
    private val onItemClick: (
        id: String,
        email: String,
        first_name: String,
        last_name: String,
        avatar: String
    ) -> Unit
) :
    ListAdapter<User, UsersAdapter.UsersViewHolder>(UsersDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = parent.inflate(R.layout.users_list_item)
        return UsersViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class UsersViewHolder(
        itemView: View,
        onItemClick: (id: String, email: String, first_name: String, last_name: String, avatar: String) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var id: String = ""
        private var email: String = ""
        private var first_name: String = ""
        private var last_name: String = ""
        private var avatar: String = ""

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(id, email, first_name, last_name, avatar)
            }
        }

        fun bind(user: User) {
            itemView.tvName.text = user.first_name
            itemView.tvSurname.text = user.last_name
            id = user.id
            email = user.email
            first_name = user.first_name
            last_name = user.last_name
            avatar = user.avatar
        }
    }

    class UsersDiffUtilCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}