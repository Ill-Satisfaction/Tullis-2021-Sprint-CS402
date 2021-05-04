package com.example.todomkvi

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.todomkvi.singletons.Singleton
import com.example.todomkvi.ui.main.SectionsPagerAdapter
import com.example.todomkvi.ui.main.fragments.BacklogFragment
import com.example.todomkvi.ui.main.fragments.FragmentInterfaceMainActivity
import com.example.todomkvi.ui.main.fragments.IndexFragment
import com.example.todomkvi.ui.main.fragments.TodayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init database
        Singleton.buildDatabase(this)

        // setup activity layout
        val sections = arrayOf (
            IndexFragment() as FragmentInterfaceMainActivity,
            TodayFragment(),
            BacklogFragment()
        )

        val sectionsPagerAdapter = SectionsPagerAdapter(this, sections, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        // setup fab
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            startActivity(Intent(this, NewTidbitActivity::class.java))
        }
    }
}