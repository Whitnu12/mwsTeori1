package com.example.resttest

import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("posts")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @FormUrlEncoded
    @PUT("Posts/id")
    fun putPost(
        @Path("id") id: Int,
        @Field("userId") userId:Int,
        @Field("id") idField:Int,
        @Field("title") title:String?,
        @Field("body") text:String?
    ):Call<PostResponse>

    @FormUrlEncoded
    @PATCH("Posts/id")
    fun patchPost(
        @Path("id") id: Int,
        @Field("userId") userId:Int,
        @Field("id") idField:Int,
        @Field("title") title:String?,
        @Field("body") text:String?
    ):Call<PostResponse>
}