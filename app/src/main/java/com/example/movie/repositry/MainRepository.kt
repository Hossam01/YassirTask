package com.example.movie.repositry

import com.example.movie.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getArticales() = apiService.getData()
}