package com.example.testproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.Reddit_Json.RedditClass
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var redditPostAdapter: RedditPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycleView()


        val serviseGenerator = ServiceGenerator.buildService(ApiServise::class.java)
        val call = serviseGenerator.getPosts()
                call.enqueue(object:Callback<RedditClass>{
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        if(response.body()?.data?.children?.size!=null)
                        for(i in 0 until response.body()?.data?.children!!.size){
                            redditPostAdapter.addPost(RedditPost(
                                response.body()?.data?.children?.get(i)?.data?.subreddit_name_prefixed,
                                response.body()?.data?.children?.get(i)?.data?.created_utc,
                                response.body()?.data?.children?.get(i)?.data?.thumbnail,
                                response.body()?.data?.children?.get(i)?.data?.title,
                                response.body()?.data?.children?.get(i)?.data?.num_comments,
                                response.body()?.data?.children?.get(i)?.data?.permalink))
                        }

                    }
               }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }
            })
    }



    private fun initRecycleView(){
        redditPostAdapter = RedditPostAdapter()
        with(recView){
            this.layoutManager=LinearLayoutManager(context)
            this.adapter=redditPostAdapter
            this.setHasFixedSize(true)
        }
    }
}

//
// https://www.reddit.com/dev/api