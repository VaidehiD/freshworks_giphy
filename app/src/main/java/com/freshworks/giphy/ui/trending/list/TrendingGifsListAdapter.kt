package com.freshworks.giphy.ui.trending.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.giphy.databinding.ListItemGifBinding

class TrendingGifsListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<GifsResponseModel, TrendingGifsListAdapter.TrendingGifsViewHolder>(
        TrendingGifsCallback
    ) {

    private var gifsList: MutableList<GifsResponseModel> = mutableListOf()

    fun submit(list: MutableList<GifsResponseModel>) {
        gifsList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingGifsViewHolder {
        return TrendingGifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class TrendingGifsViewHolder(
        val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: GifsResponseModel) {
            binding.apply {
                data = item
            }
        }
    }

    override fun onBindViewHolder(holder: TrendingGifsViewHolder, position: Int) {
        val gif = gifsList.get(position)
        holder.apply {
            binding.ivGifFavorite.setOnClickListener {
                onClickListener.onClick(gif)
            }
            bindItems(gif)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return gifsList.size
    }

    companion object TrendingGifsCallback : DiffUtil.ItemCallback<GifsResponseModel>() {
        override fun areItemsTheSame(oldItem: GifsResponseModel, newItem: GifsResponseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifsResponseModel, newItem: GifsResponseModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (gifsResponseModel: GifsResponseModel) -> Unit) {
        fun onClick(gifsResponseModel: GifsResponseModel) = clickListener(gifsResponseModel)
    }
}