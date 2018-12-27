package com.whatsclone.muhammadfaizan.travelguard.FriendLocation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R

class FriendsLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var bundle: Bundle
    private lateinit var uid: String
    private lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_friends_location)
        bundle = intent.extras
        uid = bundle.getString("uid")
        ref = FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(uid)
        var mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getUserLoc()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@FriendsLocation, MainActivity::class.java))
        this@FriendsLocation.finish()
    }

    private fun getUserLoc() {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                var map = p0.value!! as HashMap<String, Any>
                var lat: String = map["latitude"].toString()
                var lng: String = map["longitude"].toString()
                var location_enabled: Boolean = map["location_enabled"] as Boolean

                setMarker(lat, lng, location_enabled)
            }
        })
    }

    private fun setMarker(lat : String, lng : String, status : Boolean){
        var ltlng : LatLng = LatLng(lat.toDouble(), lng.toDouble())
        if (status) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ltlng, 15.5f))
            var marker : MarkerOptions = MarkerOptions().position(ltlng)
            mMap.addMarker(marker)
        }
    }
}
