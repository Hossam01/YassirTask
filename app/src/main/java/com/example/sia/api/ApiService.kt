package com.example.sia.api

import com.example.sia.models.Articles
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("7.json?api-key=TEAuM0h0GA8YV9WMq5YAZZCw2jA8QY5L")
    suspend fun getData():Response<Articles>
}