package data

import kotlinx.serialization.Serializable

@Serializable
data class VideoItem(
    val statistics: VideoStatistics,
)