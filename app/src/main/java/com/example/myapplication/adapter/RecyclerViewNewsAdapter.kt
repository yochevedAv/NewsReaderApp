package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.api.ResultX
import com.example.myapplication.data.Resource
import com.example.myapplication.databinding.NewsItemBinding

class RecyclerViewNewsAdapter(private val myData: List<ResultX>) :
    RecyclerView.Adapter<RecyclerViewNewsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResultX = myData[position]
        holder.binding.titleTextView.text = currentResultX.title
        holder.binding.subtitleTextView.text = currentResultX.description
        // Load image using Glide or any other library
        Glide.with(holder.itemView)
            .load(currentResultX.image_url)
            .into(holder.binding.imageView)
    }

    override fun getItemCount() = myData.size

    class MyViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
}
