package com.example.retrofitstewdzasan.api

import com.example.retrofitstewdzasan.model.Post
import retrofit2.Response
import retrofit2.http.*

interface PostApi {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Response<Post>

    @GET("posts")
    suspend fun getPostListByUserId(@Query("userId") userId: Int): Response<List<Post>>

    @GET("posts")
    suspend fun getPostListByUserId(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    @GET("posts")
    suspend fun getPostListByUserId2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId:Int,
        @Field("id") id:Int,
        @Field("title") title:String,
        @Field("body") body:String,
    ): Response<Post>


}