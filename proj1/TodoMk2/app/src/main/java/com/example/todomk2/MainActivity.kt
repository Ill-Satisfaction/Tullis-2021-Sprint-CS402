package com.example.todomk2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.todomk2.Adapters.SectionsPagerAdapter
import com.example.todomk2.UI.Activities.Fragments.BacklogListFragment
import com.example.todomk2.UI.Activities.Fragments.TodayListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup variables
        var sectionsPageAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager,
                arrayOf( TodayListFragment(), BacklogListFragment())
            )
        val viewPager :ViewPager = findViewById(R.id.view_pager)
        val tabs :TabLayout = findViewById(R.id.tabs)
        val fab :FloatingActionButton = findViewById(R.id.fab)

        // setup viewpager
        viewPager.adapter = sectionsPageAdapter
        tabs.setupWithViewPager(viewPager)

        // setup fab
        fab.setOnClickListener {
            val i = Intent(this, CreateNewTidbitActivity::class.java)
            startActivity(i)
        }
    }
}