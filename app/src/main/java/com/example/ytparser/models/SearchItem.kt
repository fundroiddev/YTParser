package data

import kotlinx.serialization.Serializable

@Serializable
data class SearchItem(
    val id: VideoId,
    val snippet: Snippet,
)
