package com.kustit.edu.travelguard.EditUserProfile.Contracts

interface MainContract {
    interface IModel {
        fun validate(): Boolean
    }

    interface IView {
        fun hideProgress()
        fun onSaveClickResult(result: Boolean)
        fun onFirebaseResult(result : Boolean, message : String)
    }

    interface IPresenter {
        fun onSaveClicked(uri: String, userName: String)
        fun saveUserToFB()
    }
}