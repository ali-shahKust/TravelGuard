package com.whatsclone.muhammadfaizan.travelguard.LocationSettings

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.CheckBox
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.R

class LocationSettingsActivity : AppCompatActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var locationCheckBox: CheckBox
    private var perm = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_settings)
        initViews()
        checkPerm()

        locationCheckBox.setOnClickListener { view ->
            setState()

        }
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.mToolbar)
        locationCheckBox = findViewById(R.id.location_check_box)
    }

    private fun setState() {
        if (locationCheckBox.isChecked) {
            //TODO : start location service
            Toast.makeText(this@LocationSettingsActivity, "Checked", Toast.LENGTH_SHORT).show()
        } else {
            //TODO : Stop location service
            Toast.makeText(this@LocationSettingsActivity, "Un_Checked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPerm() {
        var permArray = arrayOfNulls<String>(2)
        permArray[0] = android.Manifest.permission.ACCESS_COARSE_LOCATION
        permArray[1] = android.Manifest.permission.ACCESS_FINE_LOCATION

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
}
