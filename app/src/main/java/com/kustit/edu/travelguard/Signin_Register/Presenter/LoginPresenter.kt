package com.kustit.edu.travelguard.Signin_Register.Presenter

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kustit.edu.travelguard.Signin_Register.Model.ILoginModel
import com.kustit.edu.travelguard.Signin_Register.Model.LoginModel
import com.kustit.edu.travelguard.Signin_Register.View.ILoginView
import es.dmoral.toasty.Toasty

class LoginPresenter constructor(iLoginView: ILoginView, context: Context) : ILoginPresenter {

    private var iLoginView: ILoginView = iLoginView
    private var iLoginModel: ILoginModel? = null
    private var context: Context = context

    override fun onLoginInitiated(userName: String, userPass: String, rePass: String?) {
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

    override fun authenticateUser(flag: String, email: String, pass: String) {
        if (flag == "register") {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var map = HashMap<String, Any>()
                    map["email"] = email
                    map["password"] = pass
                    map["location_enabled"] = false
                    FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(FirebaseAuth.getInstance()!!.uid.toString()).setValue(map).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            iLoginView.firebaseResponse("success")
                        } else {
                            Toasty.error(context, task.exception!!.message.toString(), Toast.LENGTH_SHORT, true).show()
                            iLoginView.hideProgress()
                        }
                    }
                } else {
                    Toasty.error(context, task.exception!!.message.toString(), Toast.LENGTH_SHORT, true).show()
                    iLoginView.hideProgress()
                }
            }
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    iLoginView.firebaseResponse("success")
                } else {
                    iLoginView.hideProgress()
                    Toasty.error(context, task.exception?.message.toString(), Toast.LENGTH_LONG, true).show()
                }
            }

        }

    }
}

