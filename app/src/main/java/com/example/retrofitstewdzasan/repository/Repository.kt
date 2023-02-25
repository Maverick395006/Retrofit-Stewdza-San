package com.example.retrofitstewdzasan.repository

import com.example.retrofitstewdzasan.api.RetrofitInstance
import com.example.retrofitstewdzasan.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class Repository {

    suspend fun getPost(): Flow<Response<Post>> = flow {
        emit(RetrofitInstance.postApi.getPost())
    }.flowOn(Dispatchers.IO)

    suspend fun getPostById(id: Int): Flow<Response<Post>> = flow {
        emit(RetrofitInstance.postApi.getPostById(id))
    }.flowOn(Dispatchers.IO)

    suspend fun getPostListByUserId(userId: Int): Flow<Response<List<Post>>> = flow {
        emit(RetrofitInstance.postApi.getPostListByUserId(userId))
    }.flowOn(Dispatchers.IO)

    suspend fun getSortedPostListByUserId(
        userId: Int,
        sort: String,
        order: String
    ): Flow<Response<List<Post>>> = flow {
        emit(RetrofitInstance.postApi.getPostListByUserId(userId, sort, order))
    }.flowOn(Dispatchers.IO)

    suspend fun getSortedPostListByUserId2(
        userId: Int,
        options: Map<String, String>
    ): Flow<Response<List<Post>>> = flow {
        emit(RetrofitInstance.postApi.getPostListByUserId2(userId, options))
    }.flowOn(Dispatchers.IO)

    suspend fun pushPost(post: Post): Response<Post> =
        RetrofitInstance.postApi.pushPost(post)

    suspend fun pushPost2(
        userId: Int,
        id: Int,
        title: String,
        body: String
    ): Response<Post> =
        RetrofitInstance.postApi.pushPost2(userId, id, title, body)

}