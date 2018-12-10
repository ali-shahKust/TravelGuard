package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen

import android.app.Activity
import android.os.Bundle
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.travelguard.R

class SplashScreen : Activity(), TaskCallback {

    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        progressBar = findViewById(R.id.myProgressBar)
        var backgroundTask = BackgroundTask(progressBar, this, this@SplashScreen)
    }

    override fun CallBack() {
       this.finish()
    }

}
