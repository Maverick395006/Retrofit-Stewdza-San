package com.example.retrofitstewdzasan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitstewdzasan.model.Post
import com.example.retrofitstewdzasan.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.properties.Delegates

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _post: MutableLiveData<Response<Post>> = MutableLiveData()
    val post: LiveData<Response<Post>> = _post

    private val _postList: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val postList: LiveData<Response<List<Post>>> = _postList

    private var dataID by Delegates.notNull<Int>()

    fun pushPost2(userId: Int,
                  id: Int,
                  title: String,
                  body: String) {
        viewModelScope.launch {
            val response = repository.pushPost2(userId, id, title, body)
            _post.value = response
        }
    }

    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            _post.value = response
        }
    }

    fun getSortedPostListByUserId2(userId: Int, options: Map<String, String>) {
        dataID = userId
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSortedPostListByUserId2(userId, options).collect {
                _postList.postValue(it)
            }
        }
    }

    fun getSortedPostListByUserId(userId: Int, sort: String, order: String) {
        dataID = userId
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSortedPostListByUserId(userId, sort, order).collect {
                _postList.postValue(it)
            }
        }
    }

    fun getPostListByUserId(id: Int) {
        dataID = id
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostListByUserId(id).collect {
                _postList.postValue(it)
            }
        }
    }

    fun getPostById(id: Int) {
        dataID = id
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostById(id).collect {
                _post.postValue(it)
            }
        }
    }

    fun getPreviousPost() {
        if (dataID <= 1) {
            getPostById(1)
        } else {
            getPostById(--dataID)
        }
    }

    fun getNextPost() {
        if (dataID >= 100) {
            getPostById(100)
        } else {
            getPostById(++dataID)
        }
    }

}