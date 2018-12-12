package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts

import android.net.Uri

interface MainContract {
    interface IModel {
        fun validate(): Boolean
    }

    interface IView {
        fun onSaveClickResult(result: Boolean)
        fun onFirebaseResult(result : Boolean, message : String)
    }

    interface IPresenter {
        fun onSaveClicked(uri: String, userName: String)
        fun saveUserToFB()
    }
}