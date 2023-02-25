package com.example.retrofitstewdzasan.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Constants {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

}

inline fun <VM : ViewModel> viewModelFactory(
    crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>) =
            f() as T
    }