package com.tullis.todomkvii.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tullis.todomkvii.R
import com.tullis.todomkvii.activities.main.TabsPagerAdapter
import com.tullis.todomkvii.activities.main.fragments.BacklogFragment
import com.tullis.todomkvii.activities.main.fragments.CurrentFragment
import com.tullis.todomkvii.data.repos.TidbitRepo
import com.tullis.todomkvii.events.GoToViewTidbitActivityEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize repositories
        TidbitRepo.subscriber = TidbitRepo.TidbitRepoSubscriber()

        // setup activity layout
        val sections :Array<Fragment> = arrayOf (
            Fragment(),
            CurrentFragment(),
            BacklogFragment ()
        )

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)

        viewPager.adapter = TabsPagerAdapter (
            sections,
            this,
            supportFragmentManager
        )
        tabs.setupWithViewPager(viewPager)

        // setup fab
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, AddNewActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun launchViewTidbitActivity (e:GoToViewTidbitActivityEvent) {
        val i = Intent(this, ViewTidbitActivity::class.java)
        i.putExtra ("tidbit", e.tidbit)

        println ("asdf tidbit " + e.tidbit.toString())

        startActivity( i )

    }
}