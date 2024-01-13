package com.asix.dikshatek.components.config

import com.asix.dikshatek.components.constants.AppConst.API_CODE
import com.asix.dikshatek.components.constants.AppConst.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest: Request = chain.request()

                // Add headers (e.g., Authorization header with your token)
                val newRequest: Request = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $API_CODE")
                    .header("Accept", "application/json")
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}