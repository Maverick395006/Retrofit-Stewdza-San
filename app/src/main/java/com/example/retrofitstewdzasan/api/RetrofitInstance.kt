package com.example.retrofitstewdzasan.api

//import com.example.retrofitstewdzasan.BuildConfig
import com.example.retrofitstewdzasan.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client= OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val postApi: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }

}