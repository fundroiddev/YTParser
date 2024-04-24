package com.example.ytparser.api

import com.example.ytparser.KEY
import com.example.ytparser.MAX_RESULT
import com.example.ytparser.SNIPPET
import com.example.ytparser.STATISTICS
import com.example.ytparser.VIDEO
import data.ChannelListResponse
import data.SearchResponse
import data.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = SNIPPET,
        @Query("type") type: String = VIDEO,
        @Query("maxResults") maxResults: Int = MAX_RESULT,
        @Query("q") query: String,
        @Query("key") apiKey: String = KEY,
    ): SearchResponse

    @GET("channels")
    suspend fun getChannelInfo(
        @Query("part") part: String = "$SNIPPET,$STATISTICS",
        @Query("id") id: String,
        @Query("key") apiKey: String = KEY
    ): ChannelListResponse

    @GET("videos")
    suspend fun getVideos(
        @Query("part") part: String = STATISTICS,
        @Query("id") id: String,
        @Query("key") apiKey: String = KEY
    ): VideoListResponse
}