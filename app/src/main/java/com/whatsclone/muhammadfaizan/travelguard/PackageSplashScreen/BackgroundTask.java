package com.whatsclone.muhammadfaizan.travelguard.PackageSplashScreen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.whatsclone.muhammadfaizan.travelguard.View.LoginView;

import es.dmoral.toasty.Toasty;

import static java.lang.Thread.sleep;

public class BackgroundTask extends AsyncTask<Void, Integer, String> {
    Context context;
    ProgressBar progressBar;
    TaskCallback callbackObj;
    BackgroundTask(Context context, ProgressBar progressbar, TaskCallback callback){
        this.context = context;
        this.progressBar = progressbar;
        this.callbackObj = callback;
    }
    @Override
    protected String doInBackground(Void... voids) {
        synchronized (this) {
            int i = 1;

            while (i <= 100) {

                try {
                    sleep(20);
                    publishProgress(i);
                    i+=1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "str";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        Intent intent = new Intent(context, LoginView.class);
        context.startActivity(intent);
        callbackObj.CallBack();
    }
}
