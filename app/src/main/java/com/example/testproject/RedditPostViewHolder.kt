package com.example.testproject


import android.os.Build
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_redditpost.view.*
import retrofit2.http.HTTP

class RedditPostViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind (redditPost: RedditPost){
        with(itemView){
            redditPost.run {
                authorTextView.text = author
                dateCreateView.text = dateCreate
                descriptionView.text= descript

                post_itemView.movementMethod = LinkMovementMethod.getInstance()
                post_itemView.text= HtmlCompat.fromHtml("<a href=$str>Read more...</a>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY)

                count_comView.text=count_com
                photoImageView
                Glide.with(context).load(img).into(photoImageView)

            }
        }
    }

}