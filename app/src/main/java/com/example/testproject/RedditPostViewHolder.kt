package com.example.testproject


import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testproject.databinding.ItemRedditpostBinding
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime


class RedditPostViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
    private lateinit var binding: ItemRedditpostBinding

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(redditPost: RedditPost, context: Context) {
        with(itemView) {
            binding = ItemRedditpostBinding.bind(itemView)
            redditPost.run {
                binding.authorTextView.text = author
                binding.dateCreateView.text = dateCreate
                binding.descriptionView.text = descript
                binding.postItemView.movementMethod = LinkMovementMethod.getInstance()
                binding.postItemView.text = HtmlCompat.fromHtml(
                    "<a href=$str>Read more...</a>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                if(is_Video!=false) binding.button.isVisible=false
                else binding.button.isVisible=true

                binding.countComView.text = count_com

                Glide.with(context).load(img).into(binding.photoImageView)


                binding.photoImageView.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }

                binding.button.setOnClickListener{
                    var imgName:String;
                    var typeString=url.substringAfterLast(".")
                    imgName = "Image + $author"
                    var request:DownloadManager.Request=DownloadManager.Request(Uri.parse(url))
                    request.setTitle("image")
                    request.setDescription("Downloading...")

                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator+imgName+"."+typeString)
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

                    var manager:DownloadManager= context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    manager.enqueue(request)

                }


            }
        }

    }
}