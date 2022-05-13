package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.Reddit_Json.RedditClass
import com.example.testproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var redditPostAdapter: RedditPostAdapter
    private lateinit var serviceGenerator:ApiServise
    private val KEY_LIST_POST_BEFORE:String = "MainActivity:ListPost_before"
    private val KEY_LIST_POST_AFTER:String = "MainActivity:ListPost_after"
    private val KEY_LIST_POST_POSITION:String = "MainActivity:ListPost_position"

    private var startBefore:String? = null
    private var startAfter:String? = null
    private var parcelable:Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initRecycleView(this)
        serviceGenerator=ServiceGenerator.buildService(ApiServise::class.java)

        parcelable = savedInstanceState?.getParcelable(KEY_LIST_POST_POSITION)
        startBefore=savedInstanceState?.getString(KEY_LIST_POST_BEFORE)
        startAfter=savedInstanceState?.getString(KEY_LIST_POST_AFTER)


        var call = serviceGenerator.getPosts(startAfter, startBefore)
                call.enqueue(object:Callback<RedditClass>{
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        getRedditPosts(response)
                        if(parcelable!=null)
                        binding.recView.layoutManager?.onRestoreInstanceState(parcelable)
                    }
               }
                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }
            })

        //parcelable=null;

        binding.button1.setOnClickListener {
            startAfter=null;
            startBefore=null;
            call.cancel()
            call = serviceGenerator.getPosts(null, null)
            call.enqueue(object : Callback<RedditClass> {
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        getRedditPosts(response)
                    }
                }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }
            });
            Thread.sleep(1000)
            binding.recView.smoothScrollToPosition(0);

        }

        binding.button2.setOnClickListener{
            startAfter=null;
            startBefore=redditPostAdapter.getBefore();
            call.cancel()
            call = serviceGenerator.getPosts(null, redditPostAdapter.getBefore())
            call.enqueue(object : Callback<RedditClass> {
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        getRedditPosts(response)
                    }
                }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }
            });
            Thread.sleep(1000)
            binding.recView.smoothScrollToPosition(0);
        }

        binding.button3.setOnClickListener{
            startAfter=redditPostAdapter.getAfter()
            startBefore=null
            call.cancel()
            call = serviceGenerator.getPosts(redditPostAdapter.getAfter(), null)
            call.enqueue(object : Callback<RedditClass> {
                override fun onResponse(
                    call: Call<RedditClass>,
                    response: Response<RedditClass>
                ) {
                    if (response.isSuccessful) {
                        getRedditPosts(response)
                    }
                }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }
            });
            Thread.sleep(1000)
            binding.recView.smoothScrollToPosition(0);
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_LIST_POST_AFTER, startAfter)
        outState.putString(KEY_LIST_POST_BEFORE, startBefore)
        outState.putParcelable(
            KEY_LIST_POST_POSITION,
            binding.recView.layoutManager?.onSaveInstanceState()
        )
    }


    private fun initRecycleView(context: Context){
        redditPostAdapter = RedditPostAdapter(context)
        with(binding.recView){
            this.layoutManager=LinearLayoutManager(context)

            this.adapter=redditPostAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun getRedditPosts(response:Response<RedditClass>){
        if(redditPostAdapter.itemCount!=0) redditPostAdapter.dropList()
        if(response.body()?.data?.children?.size!=null)
            for(i in 0 until response.body()?.data?.children!!.size){
                redditPostAdapter.addPost(RedditPost(
                    response.body()?.data?.children?.get(i)?.data?.subreddit_name_prefixed,
                    response.body()?.data?.children?.get(i)?.data?.created_utc,
                    response.body()?.data?.children?.get(i)?.data?.thumbnail,
                    response.body()?.data?.children?.get(i)?.data?.url.toString(),
                    response.body()?.data?.children?.get(i)?.data?.title,
                    response.body()?.data?.children?.get(i)?.data?.num_comments,
                    response.body()?.data?.children?.get(i)?.data?.permalink,
                    response.body()?.data?.children?.get(i)?.data?.is_video.toString()
                ))
            }
                redditPostAdapter.setAfter(response.body()?.data?.after.toString())
                redditPostAdapter.setBefore(response.body()?.data?.before.toString())

    }
}

