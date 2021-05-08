package com.tullis.todomkvii.activities.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tullis.todomkvii.R

class TabsPagerAdapter (val fragments:Array<Fragment>, val c:Context, fm:FragmentManager)
    :FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return c.getString (
            when (position) {
                0 -> R.string.tab_text_1
                1 -> R.string.tab_text_2
                2 -> R.string.tab_text_3
                else -> R.string.error
            }
        )
    }

    override fun getCount(): Int {
        return fragments.size
    }

}