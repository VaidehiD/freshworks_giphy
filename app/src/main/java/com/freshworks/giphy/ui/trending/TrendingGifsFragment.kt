package com.freshworks.giphy.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.giphy.BR
import com.freshworks.giphy.databinding.FragmentTrendingGifsBinding
import com.freshworks.giphy.ui.trending.list.OnFavoriteButtonClickedListener
import com.freshworks.giphy.ui.trending.list.TrendingGifsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TrendingGifsFragment : Fragment() {

    private lateinit var binding: FragmentTrendingGifsBinding
    private val viewModel: TrendingGifsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTrendingGifsBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewmodel)
        }
        viewModel.apply {
            bindData(binding, BR.data)
        }
        binding.gifList.adapter = TrendingGifsListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): TrendingGifsFragment {
            return TrendingGifsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }

        @BindingAdapter("gifSrc")
        @JvmStatic
        fun bindGif(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .placeholder(android.R.drawable.progress_horizontal)
                .into(view);
        }

        @BindingAdapter("listItems")
        @JvmStatic
        fun listItems(
            recyclerView: RecyclerView,
            items: MutableList<GifsResponseModel>
        ) {
            recyclerView.adapter?.let {
                (it as TrendingGifsListAdapter).submit(items)
            }
        }

        @BindingAdapter("onFavoriteButtonClicked")
        @JvmStatic
        fun favoriteButtonClicked(view: RecyclerView, listener: OnFavoriteButtonClickedListener?) {
            (view.adapter as TrendingGifsListAdapter).setFavouriteItemClickListener(listener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}