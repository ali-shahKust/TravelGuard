package com.whatsclone.muhammadfaizan.travelguard.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.Presenter.ILoginPresenter
import com.whatsclone.muhammadfaizan.travelguard.Presenter.LoginPresenter
import com.whatsclone.muhammadfaizan.travelguard.R
import es.dmoral.toasty.Toasty

class LoginView : AppCompatActivity(), ILoginView, View.OnClickListener {

    lateinit var iLoginPresenter: ILoginPresenter
    lateinit var edtUsername: EditText
    lateinit var edtPass: EditText
    lateinit var btnSignin: Button
    lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        initViews()
        setListener()
    }

    private fun initViews() {
        iLoginPresenter = LoginPresenter(this@LoginView, this)
        edtUsername = findViewById(R.id.edtUserName)
        edtPass = findViewById(R.id.edtUserPass)
        btnSignin = findViewById(R.id.btnSignin)
        btnRegister = findViewById(R.id.btnGoBack)

    }

    private fun setListener() {
        btnSignin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnSignin) {
            iLoginPresenter.onLoginInitiated(edtUsername.text.toString(), edtPass.text.toString(), null)
        } else {
            startActivity(Intent(this@LoginView, RegistrationActivity::class.java))
            this@LoginView.finish()
        }
    }

    override fun onLoginResult(result: String) {
        var rslt: String = ""
        if (result == "success") {
            Toasty.success(this, "Valid Entry Detected", Toast.LENGTH_SHORT, true).show()
            rslt = iLoginPresenter.authenticateUser("signin", edtUsername.text.toString(), edtPass.text.toString())
        } else {
            Toasty.error(this, "Invalid Credentials Entry Detected", Toast.LENGTH_SHORT, true).show()
        }
    }
}
