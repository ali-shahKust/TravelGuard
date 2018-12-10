package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen

import android.app.Activity
import android.os.Bundle
import com.whatsclone.muhammadfaizan.travelguard.R

class SplashScreen : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    public fun ednActivity(){
        finish()
    }
}
