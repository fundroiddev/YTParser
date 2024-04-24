package data

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val items: List<SearchItem>,
)
