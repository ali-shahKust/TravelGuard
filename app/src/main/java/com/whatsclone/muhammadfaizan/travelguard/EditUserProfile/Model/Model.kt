package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Model

import android.net.Uri
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts.MainContract

class Model(uri: Uri, user_name: String) : MainContract.IModel {
    private var userName: String = user_name
    private var uri: Uri = uri

    private fun getUserName(): String {
        return this.userName
    }

    private fun getUri(): Uri {
        return this.uri!!
    }

    override fun validate(): Boolean {
        if (getUri() == null && getUserName() != null) {
            return true
        } else {
            return false
        }
    }
}