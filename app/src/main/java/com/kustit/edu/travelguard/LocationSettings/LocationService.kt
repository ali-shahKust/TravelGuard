package com.kustit.edu.travelguard.LocationSettings

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LocationService : Service() {
    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
    lateinit var ref: DatabaseReference

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        ref = FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(FirebaseAuth.getInstance().uid!!)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                Log.i("dxdiag", location!!.latitude.toString())
                var map = HashMap<String, String>()
                map["latitude"] = location.latitude.toString()
                map["longitude"] = location.longitude.toString()
                ref.updateChildren(map as Map<String, String>)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {
                onCreate()
            }

            override fun onProviderDisabled(provider: String?) {
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0f, locationListener)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }
}