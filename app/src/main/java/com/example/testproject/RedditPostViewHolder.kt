package com.example.testproject

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_redditpost.view.*

class RedditPostViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
    fun bind (redditPost: RedditPost){
        with(itemView){
            redditPost.run {
                authorTextView.text = author
                dateCreateView.text=dateCreate
                descriptionView.text= descript
                post_itemView.text=str
                count_comView.text=count_com?.toString()
                Glide.with(context).load(img).into(photoImageView)

            }
        }
    }

}