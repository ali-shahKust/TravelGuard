package com.whatsclone.muhammadfaizan.travelguard.SplashScreen

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.Signin_Register.View.LoginView
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

    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.progress = values[0]!!
    }

    override fun onPostExecute(aVoid: String) {
        if (FirebaseAuth.getInstance().currentUser != null) {
            context.startActivity(Intent(context, MainActivity::class.java))
            callbackObj.CallBack()
        } else {
            val intent = Intent(context, LoginView::class.java)
            context.startActivity(intent)
            callbackObj.CallBack()
        }
    }
}
