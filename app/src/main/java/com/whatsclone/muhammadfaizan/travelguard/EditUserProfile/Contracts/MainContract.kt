package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts

import android.net.Uri

interface MainContract {
    interface IModel {
        fun validate(): Boolean
    }

    interface IView {
        fun onSaveClickResult(result: Boolean)
        fun onFirebaseResult(result : String)
    }

    interface IPresenter {
        fun onSaveClicked(uri: Uri, userName: String)
    }
}