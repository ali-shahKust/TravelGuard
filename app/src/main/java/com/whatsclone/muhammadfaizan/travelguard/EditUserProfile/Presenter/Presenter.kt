package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Presenter

import android.net.Uri
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts.MainContract
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Model.Model

class Presenter constructor(view: MainContract.IView) : MainContract.IPresenter {

    var view: MainContract.IView = view
    lateinit var model: MainContract.IModel

    override fun onSaveClicked(uri: Uri, userName: String) {
        model = Model(uri, userName)
    }
}