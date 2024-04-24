package data

import kotlinx.serialization.Serializable

@Serializable
data class ChannelInfo(
    val snippet: ChannelSnippet,
    val statistics: ChannelStatistics
)
