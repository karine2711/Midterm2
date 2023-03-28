package com.example.myapplication.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://randomuser.me"

    fun getInstance(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create();
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
