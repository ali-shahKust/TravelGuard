package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.travelguard.View.LoginView

class BackgroundTask constructor(progressBar: ProgressBar, context: Context): AsyncTask<String, Int, String>() {

    var progressBar : ProgressBar = progressBar
    var context : Context = context

    override fun doInBackground(vararg params: String?): String {

        return ""
    }

    override fun onProgressUpdate(vararg values: Int?) {

    }

    override fun onPostExecute(result: String?) {
        context.startActivity(Intent(context, LoginView::class.java))
    }
}