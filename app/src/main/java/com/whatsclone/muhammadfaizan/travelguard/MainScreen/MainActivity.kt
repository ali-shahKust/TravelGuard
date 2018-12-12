package com.whatsclone.muhammadfaizan.travelguard.MainScreen

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.whatsclone.muhammadfaizan.travelguard.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var friends: FriendsFragment
    private lateinit var people: PeopleFragment
    private lateinit var profile: ProfileFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVars()
        mainNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.item_friends -> {
                changeFragment(friends)
                return true
            }
            R.id.item_people -> {
                changeFragment(people)
                return true
            }
            R.id.item_profile -> {
                changeFragment(profile)
                return true
            }
            else -> return false
        }
    }

    private fun initVars() {
        bottomNavigationView = findViewById(R.id.mainNav)
        friends = FriendsFragment()
        people = PeopleFragment()
        profile = ProfileFragment()
        changeFragment(friends)
    }

    private fun changeFragment(fragment: Fragment) {
        var transaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrameHolder, fragment)
        transaction.commit()
    }
}
