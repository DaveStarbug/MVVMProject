package com.star.programmingtechnologies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.star.programmingtechnologies.R
import com.star.programmingtechnologies.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter (
    private val users: MutableList<User>
    ):RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            itemView.tvUserName.text = user.name
            itemView.tvEmail.text = user.email
            Glide.with(itemView.ivAvatar.context)
                .load(user.avatar)
                .into(itemView.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list: List<User>){
        users.addAll(list)
    }
}