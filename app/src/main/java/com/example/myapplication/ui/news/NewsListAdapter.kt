package com.example.myapplication.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.api.ResultX
import com.example.myapplication.databinding.NewsItemBinding

//class RecyclerViewNewsAdapter(private val myData: List<ResultX>) :
//    RecyclerView.Adapter<RecyclerViewNewsAdapter.MyViewHolder>() {

//class RecyclerViewNewsAdapter @Inject constructor(
//    private val myData: List<ResultX>
//    ):RecyclerView.Adapter<RecyclerViewNewsAdapter.MyViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentResultX = myData[position]
//        holder.binding.titleTextView.text = currentResultX.title
//        holder.binding.subtitleTextView.text = currentResultX.description
//        Glide.with(holder.itemView)
//            .load(currentResultX.image_url)
//            .into(holder.binding.imageView)
//    }
//
//    override fun getItemCount() = myData.size
//
//    class MyViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
//}


class NewsListAdapter(private val newsItems: MutableList<ResultX>) : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(context, newsItems[position])
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context,newsItem: ResultX) {
//            binding.titleTextView.text = newsItem.title
//            binding.subtitleTextView.text = newsItem.description

            binding.data = newsItem

            Glide.with(this.itemView)
            .load(newsItem.image_url)
            .override(100, 100)
            .into(binding.imageView)
        }
    }
}

