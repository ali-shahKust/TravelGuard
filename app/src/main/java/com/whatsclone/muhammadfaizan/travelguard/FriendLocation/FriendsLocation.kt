package com.whatsclone.muhammadfaizan.travelguard.FriendLocation

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*
import com.google.maps.android.clustering.ClusterManager
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R


class FriendsLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var bundle: Bundle
    private lateinit var uid: String
    private lateinit var ref: DatabaseReference
    private var bmp: Bitmap? = null
    private lateinit var mManager: ClusterManager<ClusterMarker>
    private lateinit var mRenderer: MyClusterManagerRenderer

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
                var img_url: String = map["image_url"] as String

                if (bmp == null) {
                    var reqQueue: RequestQueue = Volley.newRequestQueue(this@FriendsLocation)
                    var imgReq = ImageRequest(img_url
                            , Response.Listener { bitmap ->
                        bmp = bitmap
                        mManager = ClusterManager<ClusterMarker>(this@FriendsLocation, mMap)
                        mRenderer = MyClusterManagerRenderer(this@FriendsLocation, mMap, mManager as ClusterManager<ClusterMarker>)
                        mManager.renderer = mRenderer
                        var marker: ClusterMarker = ClusterMarker(LatLng(lat.toDouble(), lng.toDouble()), map["user_name"] as String,
                                "Current location", R.drawable.app_logo)
                        mManager.addItem(marker)
                        mManager.cluster()
                        moveCamera(lat, lng)
                    }, 200, 200, null, Response.ErrorListener { Toast.makeText(this@FriendsLocation, "Something went wrong", Toast.LENGTH_SHORT).show() })
                    reqQueue.add(imgReq)
                } else {
                    moveCamera(lat, lng)
                }
            }
        })
    }

    private fun moveCamera(lat: String, lng: String) {
        var ltlng: LatLng = LatLng(lat.toDouble(), lng.toDouble())
        mMap.animateCamera(CameraUpdateFactory.newLatLng(ltlng))
    }
}
