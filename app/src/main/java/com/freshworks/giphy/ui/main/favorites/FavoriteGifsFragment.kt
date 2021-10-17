package com.freshworks.giphy.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freshworks.giphy.BR
import com.freshworks.giphy.databinding.FragmentFavoriteGifsBinding
import com.freshworks.giphy.ui.main.list.GifsData
import com.freshworks.giphy.ui.main.list.TrendingGifsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

// Todo : Remove duplicated Fragment
class FavoriteGifsFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteGifsBinding
    private val viewModel: FavoriteGifsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteGifsBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewmodel)
        }
        viewModel.apply {
            bindData(binding, BR.data)
        }
        binding.gifList.adapter =
            TrendingGifsListAdapter(TrendingGifsListAdapter.OnClickListener(viewModel.onFavoriteClickListener))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
    }

    override fun onResume() {
        viewModel.initialize()
        super.onResume()
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        private const val TABS = "tabs"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteGifsFragment {
            return FavoriteGifsFragment().apply {
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
            items: MutableList<GifsData>
        ) {
            recyclerView.adapter?.let {
                (it as TrendingGifsListAdapter).submit(items)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}