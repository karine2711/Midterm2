package com.example.myapplication.rest


import com.example.myapplication.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/?inc=nat,name,email&results=100")
    suspend fun getUsers(): Response<UserResponse>
}