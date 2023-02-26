package com.example.retrofitstewdzasan.api

//import com.example.retrofitstewdzasan.BuildConfig
import android.os.Build
import com.example.retrofitstewdzasan.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitInstance {


    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create Custom Interceptor to apply Headers Application wide
    val headerInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Platform", "Android")
                .addHeader("X-Auth-Token", "123456789")
                .build()
            val response = chain.proceed(request)
            return response
        }
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor)
        addInterceptor(logger)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
            .baseUrl(Constants.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val postApi: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }

}