package com.example.sia.models

import java.io.Serializable

data class MediaMetadata(
    val format: String,
    val height: Int,
    var url: String,
    val width: Int
): Serializable