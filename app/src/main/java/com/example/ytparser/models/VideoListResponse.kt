package data

import kotlinx.serialization.Serializable

@Serializable
data class VideoListResponse(
    val items: List<VideoItem>,
)