package com.freshworks.giphy.ui.trending.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.giphy.BR
import com.freshworks.giphy.databinding.ListItemGifBinding

class TrendingGifsListAdapter: RecyclerView.Adapter<TrendingGifsListAdapter.TrendingGifsViewHolder>() {

    private var gifsList: MutableList<GifsResponseModel> = mutableListOf()
    private var itemClickListener: OnFavoriteButtonClickedListener? = null

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

        init {
            updateBinding(GifFavouriteItemClickBinding())
        }

        private fun updateBinding(itemClickBinding: GifFavouriteItemClickBinding) {
            binding.setVariable(
                BR.itemClickListener, itemClickBinding
            )
        }

        fun bindItems(item: GifsResponseModel, itemClickBinding: GifFavouriteItemClickBinding) {
            binding.apply {
                data = item
                updateBinding(itemClickBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: TrendingGifsViewHolder, position: Int) {
        val gif = gifsList.get(position)
        if (gif != null) {
            holder.bindItems(gif, GifFavouriteItemClickBinding(itemClickListener, gif))
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return gifsList.size
    }

    fun setFavouriteItemClickListener(itemClickListener: OnFavoriteButtonClickedListener?) {
        this.itemClickListener = itemClickListener
    }
}

private class TrendingGifsCallback : DiffUtil.ItemCallback<GifsResponseModel>() {
    override fun areItemsTheSame(oldItem: GifsResponseModel, newItem: GifsResponseModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GifsResponseModel, newItem: GifsResponseModel): Boolean {
        return oldItem == newItem
    }
}
