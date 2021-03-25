package com.example.sia.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediametadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
): Serializable