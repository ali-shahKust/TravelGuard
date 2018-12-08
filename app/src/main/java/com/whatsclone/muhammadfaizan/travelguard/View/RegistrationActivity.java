package com.whatsclone.muhammadfaizan.travelguard.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whatsclone.muhammadfaizan.travelguard.Presenter.ILoginPresenter;
import com.whatsclone.muhammadfaizan.travelguard.Presenter.LoginPresenter;
import com.whatsclone.muhammadfaizan.travelguard.R;

import org.jetbrains.annotations.NotNull;

import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private ILoginPresenter loginPresenter;
    private EditText edtUserName;
    private EditText edtUserPass;
    private EditText edtRePass;
    private Button btnRegister;
    private Button btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        setListeners();
    }

    private void initViews() {
        loginPresenter = new LoginPresenter(RegistrationActivity.this);
        edtUserName = findViewById(R.id.edtUserName);
        edtUserPass = findViewById(R.id.edtUserPass);
        edtRePass = findViewById(R.id.edtUserPassReenter);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoBack = findViewById(R.id.btnGoBack);
    }

    private void setListeners() {
        btnGoBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            loginPresenter.onLoginInitiated(edtUserName.getText().toString(), edtUserPass.getText().toString(), edtRePass.getText().toString());
        } else {
            startActivity(new Intent(RegistrationActivity.this, LoginView.class));
            RegistrationActivity.this.finish();
        }
    }

    @Override
    public void onLoginResult(@NotNull String result) {
        if (result.equals("success")) {
            Toasty.success(this, "Registration Successful", Toast.LENGTH_SHORT, true).show();
        } else {
            Toasty.error(this, "Registration Failed", Toast.LENGTH_SHORT, true).show();
        }
    }
}
