package com.freshworks.giphy.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.freshworks.giphy.R
import com.freshworks.giphy.ui.main.favorites.FavoriteGifsFragment

enum class Tabs(name: String) {
    TRENDING("Trending"),
    FAVORITE("Favorite")
}

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> GifsFragment.newInstance(position, Tabs.TRENDING)
            else -> FavoriteGifsFragment.newInstance(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Tabs.values()[position].name
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return Tabs.values().size
    }
}