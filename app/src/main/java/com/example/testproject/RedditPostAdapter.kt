package com.example.testproject

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class RedditPostAdapter(context: Context): RecyclerView.Adapter<RedditPostViewHolder>() {

    private val postlist = mutableListOf<RedditPost>()
    private var after:String?=null;
    private var before:String?=null;
    private var context:Context?=context;

    fun addPost(redditPost: RedditPost){
        postlist.add(redditPost)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val itemView: View? = LayoutInflater.from(parent.context).inflate(R.layout.item_redditpost, parent, false)
        return RedditPostViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        holder.bind(postlist[position], context!!)

    }

    fun dropList(){
        postlist.clear()
    }

    override fun getItemCount() = postlist.size

    fun setAfter(after: String?){
        this.after=after
    }

    fun setBefore(before: String?){
        this.before=before
    }

    fun getAfter(): String?{
        return after
    }

    fun getBefore(): String?{
        return before
    }


}