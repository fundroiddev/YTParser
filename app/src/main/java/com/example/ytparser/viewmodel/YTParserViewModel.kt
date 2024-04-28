package com.example.ytparser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ytparser.models.Resource
import com.example.ytparser.repository.YTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import data.VideoWithInfoUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class YTParserViewModel @Inject constructor(
    private val ytRepository: YTRepository,
): ViewModel() {

    private val _videoData = MutableLiveData<Resource<List<VideoWithInfoUi>>>()
    val videoData:LiveData<Resource<List<VideoWithInfoUi>>> = _videoData

    fun loadQuery(query: String) {
        if (query.isBlank()) {
            return
        }
        _videoData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val searchResponse = ytRepository.searchVideos(query = query)

                val videoChannelInfo = searchResponse.items.map { video ->
                    val channelInfo = ytRepository.getChannelInfo(id = video.snippet.channelId).items.first()
                    val videoInfo = ytRepository.getVideos(id = video.id.videoId).items.first()
                    VideoWithInfoUi(
                        video.snippet.title.replace(AMP, ""),
                        video.snippet.thumbnails.default.url,
                        videoInfo.statistics.viewCount,
                        channelInfo.statistics.subscriberCount,
                        channelInfo.snippet.customUrl,
                    )
                }

                val sortedVideos = videoChannelInfo.sortedByDescending { it.viewCount.toLong() }
                _videoData.postValue(Resource.Success(sortedVideos))
            } catch (e: Exception) {
                _videoData.postValue(Resource.Error(ERROR))
            }
        }
    }

    private companion object {
        const val ERROR = "Ошибка"
        const val AMP = " &amp;"
    }
}