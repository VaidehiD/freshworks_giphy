package com.freshworks.giphy.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freshworks.giphy.R
import com.freshworks.giphy.databinding.ListItemGifBinding
import java.util.*


private const val IS_FAVORITE_UPDATED = "IS_FAVORITE_UPDATED"

class TrendingGifsListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<GifsData, TrendingGifsListAdapter.GifsViewHolder>(
        TrendingGifsCallback()
    ) {

    private var gifsList: MutableList<GifsData> = mutableListOf()

    fun submit(list: MutableList<GifsData>) {
        gifsList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class GifsViewHolder(
        val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: GifsData) {
            binding.apply {
                data = item
            }
        }
    }

    override fun onBindViewHolder(holder: GifsViewHolder, pos: Int) {
        onBindViewHolder(holder, pos, Collections.emptyList())
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int, payload: List<Any>) {
        val gif = gifsList[position]

        holder.apply {
            binding.ivGifFavorite.setOnClickListener {
                onClickListener.onClick(position, gif)
            }
            binding.ivGifNotFavorite.setOnClickListener {
                onClickListener.onClick(position, gif)
            }
        }

        if (payload.isEmpty() || payload[0] !is Bundle) {
            holder.apply {
                bindItems(gif)
                binding.executePendingBindings()
            }
        } else {
            val isFavorite = (payload[0] as Bundle).getBoolean(IS_FAVORITE_UPDATED)
            gif.favoriteIcon.value = if (isFavorite) {
                R.drawable.favorite
            } else {
                R.drawable.favorite_border
            }
        }
    }

    override fun getItemCount(): Int {
        return gifsList.size
    }

    private class TrendingGifsCallback : DiffUtil.ItemCallback<GifsData>() {
        override fun areItemsTheSame(oldItem: GifsData, newItem: GifsData) =
            oldItem.id.value == newItem.id.value && oldItem.isFavorite.value == newItem.isFavorite.value

        override fun areContentsTheSame(oldItem: GifsData, newItem: GifsData): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: GifsData, newItem: GifsData): Any? {
            if (oldItem.id == newItem.id) {
                return if (oldItem.isFavorite == newItem.isFavorite) {
                    super.getChangePayload(oldItem, newItem)
                } else {
                    val diff = Bundle()
                    newItem.isFavorite.value?.let {
                        diff.putBoolean(IS_FAVORITE_UPDATED, it)
                    }
                }
            }
            return super.getChangePayload(oldItem, newItem)
        }
    }

    class OnClickListener(val clickListener: (position: Int, gifsData: GifsData) -> Unit) {
        fun onClick(position: Int, gifsData: GifsData) = clickListener(position, gifsData)
    }
}