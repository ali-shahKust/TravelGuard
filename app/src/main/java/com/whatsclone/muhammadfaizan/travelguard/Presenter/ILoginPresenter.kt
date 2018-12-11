package com.whatsclone.muhammadfaizan.travelguard.Presenter

interface ILoginPresenter {
    fun onLoginInitiated(userName: String, userPass: String, rePass : String?)
    fun authenticateUser(flag : String, email : String, pass : String)
}