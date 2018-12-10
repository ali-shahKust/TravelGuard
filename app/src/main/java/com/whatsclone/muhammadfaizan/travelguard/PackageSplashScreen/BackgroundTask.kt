package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.View.LoginView
import es.dmoral.toasty.Toasty
import java.lang.Thread.sleep

class BackgroundTask constructor(progressBar: ProgressBar, context: Context, callBackObj : TaskCallback): AsyncTask<String, Int, String>() {

    var progressBar : ProgressBar = progressBar
    var taskCallbackObj : TaskCallback = callBackObj
    var context : Context = context

    override fun doInBackground(vararg params: String?): String {
        synchronized(this) {
            for (i in 1..100) {
                try {
                    sleep(5)
                    publishProgress(i)
                } catch (exc : Exception) {

                }
            }
        }
        return ""
    }

    override fun onProgressUpdate(vararg p0: Int?) {
        progressBar.progress = p0[0]!!
    }

    override fun onPostExecute(result: String?) {
       // context.startActivity(Intent(context, LoginView::class.java))
        Toasty.success(context, "Success", Toast.LENGTH_SHORT, true).show()
        //taskCallbackObj.CallBack()
    }
}