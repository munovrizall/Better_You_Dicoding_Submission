package com.artonov.betteryou.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artonov.betteryou.databinding.ItemRowBookBinding
import com.artonov.betteryou.model.Book
import com.bumptech.glide.Glide

class ListBookAdapter(private val listBook: ArrayList<Book>) :
    RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }

    inner class ListViewHolder(var binding: ItemRowBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, author, year, description, photo) = listBook[position]
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.binding.bookPhoto)
        holder.apply {
            binding.tvName.text = name
            binding.tvAuthor.text = author
            binding.tvDescription.text = description
            itemView.setOnClickListener() {
                onItemClickCallback.onItemClicked(listBook[holder.adapterPosition])
            }
        }
    }
}