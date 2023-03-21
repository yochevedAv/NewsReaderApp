package com.example.myapplication.adapters

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.api.Article
import com.example.myapplication.api.ResultX

class recyclerViewNewsAdapter (private val dataSet: MutableList<ResultX>,private val context: Context) :
    RecyclerView.Adapter<recyclerViewNewsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val titleTv: TextView
         val subTitleTv: TextView
         val image: ImageView

        init {
            // Define click listener for the ViewHolder's View
            titleTv = view.findViewById(R.id.news_title)
            subTitleTv = view.findViewById(R.id.news_sub_title)
            image = view.findViewById(R.id.news_img)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.news_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.titleTv.text = dataSet[position].title
        viewHolder.subTitleTv.text = dataSet[position].content

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
        Glide.with(context)
            .load(dataSet[position].image_url)
            .apply(requestOptions)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(viewHolder.image)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}