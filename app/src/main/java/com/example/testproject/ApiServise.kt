package com.example.testproject


import com.example.testproject.Reddit_Json.RedditClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiServise {
    @GET("/top.json")
    fun getPosts(): Call<RedditClass>
}