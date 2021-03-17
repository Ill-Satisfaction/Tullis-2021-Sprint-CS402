package com.example.todomk2.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.todomk2.Interfaces.ListFragmentInterface

class SectionsPagerAdapter  (   private val context :Context,
                                fm :FragmentManager,
                                val frags :Array<ListFragmentInterface>)
                            :FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return frags[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return frags[position].getTitle()
    }

    override fun getCount(): Int {
        return frags.size
    }
}