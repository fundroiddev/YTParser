package data

import kotlinx.serialization.Serializable

@Serializable
data class ChannelListResponse(
    val items: List<ChannelInfo>
)
