package com.whatsclone.muhammadfaizan.travelguard.Signin_Register.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R
import com.whatsclone.muhammadfaizan.travelguard.Signin_Register.Presenter.ILoginPresenter
import com.whatsclone.muhammadfaizan.travelguard.Signin_Register.Presenter.LoginPresenter
import es.dmoral.toasty.Toasty

class LoginView : AppCompatActivity(), ILoginView, View.OnClickListener {

    private lateinit var iLoginPresenter: ILoginPresenter
    private lateinit var edtUsername: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnSignin: Button
    private lateinit var btnRegister: Button
    private lateinit var progressBar: ProgressBar
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
        progressBar = findViewById(R.id.activity_login_progress)

    }

    private fun setListener() {
        btnSignin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnSignin) {
            iLoginPresenter.onLoginInitiated(edtUsername.text.toString(), edtPass.text.toString(), null)
            progressBar.visibility = View.VISIBLE
        } else {
            startActivity(Intent(this@LoginView, RegistrationActivity::class.java))
            this@LoginView.finish()
        }
    }

    override fun onLoginResult(result: String) {
        if (result == "success") {
            Toasty.success(this, "Valid Entry Detected", Toast.LENGTH_SHORT, true).show()
            iLoginPresenter.authenticateUser("signin", edtUsername.text.toString(), edtPass.text.toString())
        } else {
            hideProgress()
            Toasty.error(this, "Invalid Credentials Entry Detected", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun firebaseResponse(result: String) {
        if (result == "success") {
            startActivity(Intent(this@LoginView, MainActivity::class.java))
            finish()
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        progressBar.visibility = View.INVISIBLE
    }
}
