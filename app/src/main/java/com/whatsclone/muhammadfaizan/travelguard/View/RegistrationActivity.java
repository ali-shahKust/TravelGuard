package com.whatsclone.muhammadfaizan.travelguard.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whatsclone.muhammadfaizan.travelguard.Presenter.ILoginPresenter;
import com.whatsclone.muhammadfaizan.travelguard.Presenter.LoginPresenter;
import com.whatsclone.muhammadfaizan.travelguard.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends AppCompatActivity implements ILoginView {

    private ILoginPresenter loginPresenter;
    @BindViews({R.id.edtUserName, R.id.edtUserPass, R.id.edtUserPassReenter})
    List<EditText> edtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(RegistrationActivity.this, RegistrationActivity.this);
    }

    @OnClick({R.id.btnRegister, R.id.btnGoBack})
    void buttonListener(View v) {
        if (v.getId() == R.id.btnRegister) {
            loginPresenter.onLoginInitiated(edtList.get(0).getText().toString(), edtList.get(1).getText().toString(), edtList.get(2).getText().toString());
        } else {
            startActivity(new Intent(RegistrationActivity.this, LoginView.class));
            RegistrationActivity.this.finish();
        }
    }

    @Override
    public void onLoginResult(@NotNull String result) {
        String rslt = "";
        if (result.equals("success")) {
            Toasty.success(this, "Valid Entry Detected", Toast.LENGTH_SHORT, true).show();
            rslt = loginPresenter.authenticateUser("register", edtList.get(0).getText().toString(), edtList.get(1).getText().toString());
        } else {
            Toasty.error(this, "Invalid Credentials Entry Detected", Toast.LENGTH_SHORT, true).show();
        }
    }
}
