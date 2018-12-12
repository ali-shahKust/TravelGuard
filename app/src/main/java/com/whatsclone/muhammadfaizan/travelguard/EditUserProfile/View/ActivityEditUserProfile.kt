package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts.MainContract
import com.whatsclone.muhammadfaizan.travelguard.R

class ActivityEditUserProfile : AppCompatActivity(), MainContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_profile)
    }

    override fun onSaveClickResult(result: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFirebaseResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
