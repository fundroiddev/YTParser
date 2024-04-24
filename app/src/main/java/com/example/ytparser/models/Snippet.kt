package data

import kotlinx.serialization.Serializable

@Serializable
data class Snippet(
    val title: String,
    val thumbnails: Thumbnails,
    val channelId: String,
)
