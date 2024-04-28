package com.example.ytparser.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.ytparser.databinding.YtparserDataItemBinding
import data.VideoWithInfoUi


internal class YTDataAdapter(
    private val data: List<VideoWithInfoUi>,
) : RecyclerView.Adapter<YTDataAdapter.YTViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YTViewHolder {
        val viewItem = YtparserDataItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return YTViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: YTViewHolder, position: Int) {
        val item = data[position]
        with(holder.viewBinding) {
            title.text = item.title
            viewcount.text = "Просмотры: ${item.viewCount}"
            subscribers.text = "Подписчики: ${item.subscriberCount}"
            channel.text = "Канал: ${item.channelUrl}"

            Glide.with(root.context)
                .load(item.thumbnailUrl)
                .transform(CenterCrop())
                .into(preview)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class YTViewHolder(val viewBinding: YtparserDataItemBinding) : RecyclerView.ViewHolder(viewBinding.root)
}