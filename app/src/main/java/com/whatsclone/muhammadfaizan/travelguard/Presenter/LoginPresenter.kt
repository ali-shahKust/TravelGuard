package com.whatsclone.muhammadfaizan.travelguard.Presenter

import com.whatsclone.muhammadfaizan.travelguard.Model.ILoginModel
import com.whatsclone.muhammadfaizan.travelguard.Model.LoginModel
import com.whatsclone.muhammadfaizan.travelguard.View.ILoginView

class LoginPresenter constructor(iLoginView: ILoginView) : ILoginPresenter {
    var iLoginView: ILoginView = iLoginView
    var iLoginModel: ILoginModel? = null

    override fun onLoginInitiated(userName: String, userPass: String, rePass : String?) {
       if (rePass == null) {
           iLoginModel = LoginModel(userName, userPass)
           if (iLoginModel!!.validateSigninCredentials()) {
               iLoginView.onLoginResult("success")
           } else {
               iLoginView.onLoginResult("fail")
           }
       } else {
           iLoginModel = LoginModel(userName, userPass, rePass)
           if (iLoginModel!!.validateRegisterCredentials()) {
               iLoginView.onLoginResult("success")
           } else {
               iLoginView.onLoginResult("fail")
           }
       }
    }

}

