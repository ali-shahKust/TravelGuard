package com.kustit.edu.travelguard.EditUserProfile.Model

import com.kustit.edu.travelguard.EditUserProfile.Contracts.MainContract

class Model(uri: String, user_name: String) : MainContract.IModel {
    private var userName: String = user_name
    private var uri: String = uri

    private fun getUserName(): String {
        return this.userName
    }

    private fun getUri(): String {
        return this.uri
    }

    override fun validate(): Boolean {
        if (getUri() !=  "" && getUserName() != "") {
            return true
        } else {
            return false
        }
    }
}