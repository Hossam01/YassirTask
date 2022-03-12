package com.example.movie.api

import com.example.movie.models.MovieDiscoverModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getData():Response<MovieDiscoverModel>
}