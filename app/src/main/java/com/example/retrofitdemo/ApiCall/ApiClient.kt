package com.example.retrofitdemo.ApiCall

import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.PostComment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiClient{
    @GET("/posts")
    fun getPosts(): Call<List<Post>>


    @GET("/posts/{id}/comments")
    fun getPostcomments(
        @Path("id") id: Int
    ) : Call<List<PostComment>>

}