package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                            response.body()?.data?.children?.get(4)?.data?.title?.toString().toString()
                        )
                    }
                }

                override fun onFailure(call: Call<RedditClass>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error12", t.message.toString())
                }


            })
        }

    }

}

//
// https://www.reddit.com/dev/api