package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.ProgressBar
import com.whatsclone.muhammadfaizan.travelguard.View.LoginView
import java.lang.Thread.sleep

class BackgroundTask internal constructor(internal var context: Context, internal var progressBar: ProgressBar, internal var callbackObj: TaskCallback) : AsyncTask<Void, Int, String>() {
    override fun doInBackground(vararg voids: Void): String {
        synchronized(this) {
            var i = 1

            while (i <= 100) {

                try {
                    sleep(20)
                    publishProgress(i)
                    i += 1
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
        return "str"
    }

    protected override fun onProgressUpdate(vararg values: Int?) {
        progressBar.progress = values[0]!!
    }

    override fun onPostExecute(aVoid: String) {
        val intent = Intent(context, LoginView::class.java)
        context.startActivity(intent)
        callbackObj.CallBack()
    }
}
