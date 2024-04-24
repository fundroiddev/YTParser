package com.example.ytparser.repository

import com.example.ytparser.api.YouTubeApiService
import data.ChannelListResponse
import data.SearchResponse
import data.VideoListResponse
import javax.inject.Inject

internal class YTRepository @Inject constructor(private val ytService: YouTubeApiService) {

    suspend fun searchVideos(query: String) : SearchResponse {
        return ytService.searchVideos(
            query = query,
        )
    }

    suspend fun getChannelInfo(id: String): ChannelListResponse {
        return ytService.getChannelInfo(
            id = id,
        )
    }

    suspend fun getVideos(id: String): VideoListResponse {
        return ytService.getVideos(
            id = id,
        )
    }
}