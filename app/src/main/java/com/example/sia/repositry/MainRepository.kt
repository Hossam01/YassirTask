package com.example.sia.repositry

import com.example.sia.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getArticales() = apiService.getData()
}