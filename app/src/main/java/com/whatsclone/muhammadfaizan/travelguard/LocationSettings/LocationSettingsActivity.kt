package com.whatsclone.muhammadfaizan.travelguard.LocationSettings

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.CheckBox
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R
import es.dmoral.toasty.Toasty

class LocationSettingsActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var locationCheckBox: CheckBox
    private var perm = false
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_settings)
        initViews()
        checkService()
        checkPerm()

        locationCheckBox.setOnClickListener { view ->
            setState()

        }
    }

    private fun checkService() {
        if (pref.getBoolean("is_loc_running", false)) {
            locationCheckBox.isChecked = true
        } else {
            locationCheckBox.isChecked = false
        }
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.mToolbar)
        locationCheckBox = findViewById(R.id.location_check_box)
        pref = getSharedPreferences("location_service", Context.MODE_PRIVATE)

        mToolbar.title = "Location Settings"
        mToolbar.subtitle = "Enable or disable location service"
        mToolbar.setNavigationIcon(R.drawable.ic_go_back)
        mToolbar.setTitleTextColor(Color.WHITE)
        mToolbar.setSubtitleTextColor(Color.WHITE)
        mToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@LocationSettingsActivity, MainActivity::class.java))
            this@LocationSettingsActivity.finish()
        }

    }

    private fun setState() {
        if (locationCheckBox.isChecked) {
            var editor = pref.edit()
            editor.putBoolean("is_loc_running", true)
            editor.apply()
            startService(Intent(this@LocationSettingsActivity, LocationService::class.java))
            Toasty.success(this@LocationSettingsActivity, "Your friends can now see your location", Toast.LENGTH_SHORT, true).show()
        } else {
            var editor: SharedPreferences.Editor = pref.edit()
            editor.putBoolean("is_loc_running", false)
            editor.apply()
            var intent = Intent(this@LocationSettingsActivity, LocationService::class.java)
            stopService(intent)
            Toasty.error(this@LocationSettingsActivity, "Your friends can no longer see your location", Toast.LENGTH_SHORT, true).show()
        }
    }

    private fun checkPerm() {
        var permArray = arrayOfNulls<String>(2)
        permArray[0] = Manifest.permission.ACCESS_COARSE_LOCATION
        permArray[1] = Manifest.permission.ACCESS_FINE_LOCATION

        if (ContextCompat.checkSelfPermission(this@LocationSettingsActivity, permArray[0].toString()) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this@LocationSettingsActivity, permArray[1].toString()) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@LocationSettingsActivity, permArray, 69)
            locationCheckBox.isEnabled = false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 69 && grantResults.isNotEmpty()) {
            for (permission in grantResults) {
                if (permission == PackageManager.PERMISSION_DENIED) {
                    var dialog = AlertDialog.Builder(this@LocationSettingsActivity, R.style.AlertDialogCustom)
                    dialog.setTitle("Warning")
                    dialog.setMessage("You will not be able to share your location if location permissions are denied")
                    dialog.setPositiveButton("Close", DialogInterface.OnClickListener { dlg, which ->
                        dlg.dismiss()
                    })

                    dialog.show()
                    perm = false
                    return
                } else {
                    perm = true
                    locationCheckBox.isEnabled = true
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@LocationSettingsActivity, MainActivity::class.java))
        this@LocationSettingsActivity.finish()
    }
}
