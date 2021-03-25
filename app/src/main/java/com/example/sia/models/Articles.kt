package com.example.sia.models

import java.io.Serializable

data class Articles(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>?=null,
    val status: String
): Serializable