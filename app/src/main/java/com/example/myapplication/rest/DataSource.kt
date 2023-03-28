package com.example.myapplication.rest

import com.example.myapplication.models.Name
import com.example.myapplication.models.User

class Datasource {
    suspend fun loadNews(): List<User> {
        return RetrofitHelper.getInstance().create(ApiService::class.java).getUsers().run {
            this.body()?.results?.map {
                User(
                    Name(it.name.title, it.name.first, it.name.last),
                    it.email, it.nat
                )
            } ?: listOf()
        }
    }
}

