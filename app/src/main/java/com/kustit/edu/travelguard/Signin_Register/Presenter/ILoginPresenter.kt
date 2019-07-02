package com.kustit.edu.travelguard.Signin_Register.Presenter

interface ILoginPresenter {
    fun onLoginInitiated(userName: String, userPass: String, rePass : String?)
    fun authenticateUser(flag : String, email : String, pass : String)
}