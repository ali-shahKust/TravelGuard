package com.whatsclone.muhammadfaizan.travelguard.View

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        initViews()
        setListener()
    }

    private fun initViews() {
        iLoginPresenter = LoginPresenter(this@LoginView)
        edtUsername = findViewById(R.id.edtUserName)
        edtPass = findViewById(R.id.edtUserPass)
        btnSignin = findViewById(R.id.btnSignin)

    }

    private fun setListener() {
        btnSignin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        iLoginPresenter.onLoginInitiated(edtUsername.text.toString(), edtPass.text.toString())
    }

    override fun onLoginResult(result: String) {
        if (result == "success") {
            Toasty.success(this, "Valid Credentials", Toast.LENGTH_SHORT, true).show()
        } else {
            Toasty.error(this, "Invalid Credentials", Toast.LENGTH_SHORT, true).show()
        }
    }
}
