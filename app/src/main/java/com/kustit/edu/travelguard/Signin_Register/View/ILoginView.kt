package com.kustit.edu.travelguard.Signin_Register.View

interface ILoginView {
    fun onLoginResult(result: String)
    fun firebaseResponse(result : String)
    fun hideProgress()
}