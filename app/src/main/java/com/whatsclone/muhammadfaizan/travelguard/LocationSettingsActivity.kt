package com.whatsclone.muhammadfaizan.travelguard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.CheckBox
import android.widget.Toast

class LocationSettingsActivity : AppCompatActivity() {

    private lateinit var mToolbar : Toolbar
    private lateinit var locationCheckBox : CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_settings)
        initViews()
        locationCheckBox.setOnClickListener { view ->
            setState()
        }
    }

    private fun initViews(){
        mToolbar = findViewById(R.id.mToolbar)
        locationCheckBox = findViewById(R.id.location_check_box)
    }
    private fun setState(){
        if (locationCheckBox.isChecked) {
            //TODO : start location service
            Toast.makeText(this@LocationSettingsActivity, "Checked", Toast.LENGTH_SHORT).show()
        } else {
            //TODO : Stop location service
            Toast.makeText(this@LocationSettingsActivity, "Un_Checked", Toast.LENGTH_SHORT).show()
        }
    }
}
