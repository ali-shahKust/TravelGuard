package com.kustit.edu.travelguard.SplashScreen

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.travelguard.R

class SplashScreen : Activity(), TaskCallback {

    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        progressBar = findViewById(R.id.myProgressBar)
        var backgroundTask = BackgroundTask(this, progressBar, this@SplashScreen)
        backgroundTask.execute()
    }

    override fun CallBack() {
       this.finish()
    }

}
