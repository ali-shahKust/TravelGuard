package com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Contracts.MainContract
import com.whatsclone.muhammadfaizan.travelguard.EditUserProfile.Presenter.Presenter
import com.whatsclone.muhammadfaizan.travelguard.MainScreen.MainActivity
import com.whatsclone.muhammadfaizan.travelguard.R
import de.hdodenhof.circleimageview.CircleImageView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_registration.*

class ActivityEditUserProfile : AppCompatActivity(), MainContract.IView, View.OnClickListener {

    private lateinit var edtUserName: EditText
    private lateinit var imgUser: CircleImageView
    private lateinit var btnSave: Button
    private lateinit var btnLater: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: MainContract.IPresenter
    private var uri: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_profile)
        initViews()
        btnSave.setOnClickListener(this)
        btnLater.setOnClickListener(this)
        imgUser.setOnClickListener(this)
    }

    private fun initViews() {
        edtUserName = findViewById(R.id.edt_user_name)
        imgUser = findViewById(R.id.edt_img_user)
        btnSave = findViewById(R.id.btn_save)
        btnLater = findViewById(R.id.btn_later)
        progressBar = findViewById(R.id.edt_user_profile_progress)
        presenter = Presenter(this@ActivityEditUserProfile)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_later -> {
                startActivity(Intent(this@ActivityEditUserProfile, MainActivity::class.java))
                this.finish()
            }
            R.id.btn_save -> {
                btnSave.isEnabled = false
                btnLater.isEnabled = false
                progressBar.visibility = View.VISIBLE
                presenter.onSaveClicked(uri, edtUserName.text.toString())
            }
            R.id.edt_img_user -> {
                var intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, 6)
            }
        }
    }

    override fun onSaveClickResult(result: Boolean) {
        if (result) {
            presenter.saveUserToFB()
        } else {
            hideProgress()
            Toasty.error(this, "Invalid User image or name", Toast.LENGTH_SHORT, true).show()
        }
    }

    override fun onFirebaseResult(result: Boolean, message: String) {
        if (result) {
            startActivity(Intent(this@ActivityEditUserProfile, MainActivity::class.java))
            this@ActivityEditUserProfile.finish()
        } else {
            hideProgress()
            Toasty.error(this@ActivityEditUserProfile, message, Toast.LENGTH_LONG, true).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 6 && resultCode == Activity.RESULT_OK && data != null) {
            imgUser.setImageURI(data.data)
            uri = data.data.toString()
        }
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
        btnSave.isEnabled = true
        btnRegister.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
        progressBar.visibility = View.INVISIBLE
    }
}
