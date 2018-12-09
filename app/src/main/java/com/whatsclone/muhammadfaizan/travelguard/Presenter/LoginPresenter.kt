package com.whatsclone.muhammadfaizan.travelguard.Presenter

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.whatsclone.muhammadfaizan.travelguard.Model.ILoginModel
import com.whatsclone.muhammadfaizan.travelguard.Model.LoginModel
import com.whatsclone.muhammadfaizan.travelguard.View.ILoginView
import es.dmoral.toasty.Toasty

class LoginPresenter constructor(iLoginView: ILoginView, context : Context) : ILoginPresenter {

    var iLoginView: ILoginView = iLoginView
    var iLoginModel: ILoginModel? = null
    var context : Context = context

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

    override fun authenticateUser(flag : String, email : String, pass : String): String {
        var result : String = ""
        if (flag == "register") {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var map = HashMap<String, String>()
                    map["email"] = email
                    map["password"] = pass
                    FirebaseDatabase.getInstance().getReference("TravelGuard").child("Users").child(FirebaseAuth.getInstance()!!.uid.toString()).setValue(map).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toasty.success(context, "User created successfully", Toast.LENGTH_SHORT, true).show()
                            result = "success"
                        } else {
                            Toasty.error(context, "User creation error", Toast.LENGTH_SHORT, true).show()
                            result = task.exception!!.message.toString()
                        }
                    }
                } else {
                    Toasty.error(context, "User creation error", Toast.LENGTH_SHORT, true).show()
                    result = task.exception!!.message.toString()
                }
            }
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toasty.success(context, "Signin Success", Toast.LENGTH_SHORT, true).show()
                    result = "success"
                } else {
                    result = task.exception!!.message.toString()
                }
            }
        }
        return result
    }
}

