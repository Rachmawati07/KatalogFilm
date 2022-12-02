package com.rachma.katalogfilm.api

import com.rachma.katalogfilm.api.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Untuk mendeklarasikan objek yang bernama RetrofitInstance
object RetrofitInstance {
    // Untuk mendeklarasikan variabel yang bernama retrofit
    private val retrofit by lazy{
        // Untuk menjalankan library retrofit untuk mengakses api
        // Menggunakan method Builder Untuk mengakses url dan membuat GsonConverterFactory
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Untuk mendeklarasikan variabel yang bernama api
    // Untuk membuat class SimpleApi dengan menggunakan library retrofit
    val api : SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }
}