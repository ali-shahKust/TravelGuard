package com.whatsclone.muhammadfaizan.travelguard.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.Presenter.ILoginPresenter
import com.whatsclone.muhammadfaizan.travelguard.Presenter.LoginPresenter
import com.whatsclone.muhammadfaizan.travelguard.R
import es.dmoral.toasty.Toasty

class LoginView : AppCompatActivity(), ILoginView {

    lateinit var iLoginPresenter : ILoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        initViews()
    }

    private fun initViews(){
        iLoginPresenter = LoginPresenter(this@LoginView)
    }

    override fun onLoginResult(result : String) {
        if (result.equals("success")){
            Toasty.success(this, "Valid Credentials", Toast.LENGTH_LONG, true).show()
        } else{
            Toasty.error(this, "Invalid Credentials", Toast.LENGTH_LONG, true).show()
        }
    }
}
