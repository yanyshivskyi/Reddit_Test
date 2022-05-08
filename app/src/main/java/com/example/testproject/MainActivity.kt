package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    private lateinit var redditPostAdapter: RedditPostAdapter
 //   private var redditPost:RedditPost? = RedditPost("","","","", 0, "")
    private var author: String? = null
    private var dateCreate: String?= null
    private var img: String? = null
    private var descript:String? = null
    private var count_com: Int? = null
    private var str: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycleView()

        val serviseGenerator = ServiceGenerator.buildService(ApiServise::class.java)
        val call = serviseGenerator.getPosts()
        val button = findViewById<Button>(R.id.btnClick)
        button.setOnClickListener(){
            call.enqueue(object:Callback<RedditClass>{
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        Log.e("success12",
                            response.body()?.data?.children?.get(3)?.data?.approved_at_utc?.toString().toString()
                        )
                        for(i in 0..24){

                            author = response.body()?.data?.children?.get(i)?.data?.author
                            dateCreate = "====="
                            img = response.body()?.data?.children?.get(i)?.data?.thumbnail;
                            descript = response.body()?.data?.children?.get(i)?.data?.title;
                            count_com = response.body()?.data?.children?.get(i)?.data?.num_comments
                            str="=-=-=-=-=-=";
                            redditPostAdapter.addPost(RedditPost(author, dateCreate, img, descript,count_com,str))
                        }
                    }
                }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }


            })
        }

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