package com.example.github.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.github.data.UserResponse
import com.example.github.databinding.ItemUserBinding

class Adapter(private val data: MutableList<UserResponse.Item> = mutableListOf(),
              val clickListener: ClickListener)
    : RecyclerView.Adapter<Adapter.UsersViewHolder>() {

    fun setData(data: MutableList<UserResponse.Item>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(private val view: ItemUserBinding) :RecyclerView.ViewHolder(view.root)
    { fun bind(item: UserResponse.Item){
        view.root.setOnClickListener {
            clickListener.onClick(adapterPosition, item)
        }
                view.image.load(item.avatar_url){
                    transformations(CircleCropTransformation()) }
                view.username.text = item.login
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size


}