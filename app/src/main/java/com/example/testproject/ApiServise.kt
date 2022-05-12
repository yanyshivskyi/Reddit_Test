package com.example.testproject


import com.example.testproject.Reddit_Json.RedditClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServise {
    @GET("top.json")
    fun getPosts(@Query("after") after:String?=null, @Query("before") before:String?=null,
               @Query("count") count:Int=10, @Query("limit") limit:Int=10
    ): Call<RedditClass>
}

