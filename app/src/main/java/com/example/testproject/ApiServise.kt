package com.example.testproject


import retrofit2.Call
import retrofit2.http.GET

interface ApiServise {
    @GET("/top.json")
    fun getPosts(): Call<RedditClass>
}