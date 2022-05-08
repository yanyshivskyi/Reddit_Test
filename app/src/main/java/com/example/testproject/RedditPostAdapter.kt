package com.example.testproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RedditPostAdapter: RecyclerView.Adapter<RedditPostViewHolder>() {

    private val postlist = mutableListOf<RedditPost>()
    fun addPost(redditPost: RedditPost){
        postlist.add(redditPost)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val itemView: View? = LayoutInflater.from(parent.context).inflate(R.layout.item_redditpost, parent, false)
        return RedditPostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(postlist[position])
    }

    override fun getItemCount() = postlist.size

}