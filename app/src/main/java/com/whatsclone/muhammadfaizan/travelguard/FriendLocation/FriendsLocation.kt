package com.whatsclone.muhammadfaizan.travelguard.FriendLocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R

class FriendsLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_friends_location)
        bundle = intent.extras
        Toast.makeText(this@FriendsLocation, bundle.getString("uid"), Toast.LENGTH_SHORT).show()
        var mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@FriendsLocation, MainActivity::class.java))
        this@FriendsLocation.finish()
    }
}
