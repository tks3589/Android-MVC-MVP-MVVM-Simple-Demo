package com.example.mvcmvpmvvm.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvcmvpmvvm.R;
import com.example.mvcmvpmvvm.mvp.presenter.LoginPresenter;

/**
 * @author jere
 */
public class MvpLoginActivity extends AppCompatActivity implements IMvpLoginView{
    private EditText userNameEt,passwordEt;
    private Button loginBtn,logoutBtn;
    private TextView loginUserName;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        loginUserName = findViewById(R.id.login_username);
        loginBtn = findViewById(R.id.login_btn);
        logoutBtn = findViewById(R.id.logout_btn);

        loginPresenter = new LoginPresenter(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login(userNameEt.getText().toString(),passwordEt.getText().toString());
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.logout();
            }
        });

    }

    @Override
    public void onLoginResult(Boolean isLoginSuccess) {
        if (isLoginSuccess) {
            String username = loginPresenter.getUser().getUserName();
            Toast.makeText(MvpLoginActivity.this,
                    username+ " Login Successful",
                    Toast.LENGTH_SHORT)
                    .show();
            loginUserName.setText("歡迎登入! " + username);
            loginBtn.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MvpLoginActivity.this,
                    "Login Failed",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogoutResult(){
        loginUserName.setText("尚未登入");
        loginBtn.setVisibility(View.VISIBLE);
        logoutBtn.setVisibility(View.GONE);
    }
}
