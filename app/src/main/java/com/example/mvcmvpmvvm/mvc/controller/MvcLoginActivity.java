package com.example.mvcmvpmvvm.mvc.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvcmvpmvvm.R;
import com.example.mvcmvpmvvm.mvp.model.User;

/**
 * @author jere
 */
public class MvcLoginActivity extends AppCompatActivity {
    private EditText userNameEt,passwordEt;
    private Button loginBtn,logoutBtn;
    private TextView loginUserName;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_login);

        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        logoutBtn = findViewById(R.id.logout_btn);
        loginUserName = findViewById(R.id.login_username);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(userNameEt.getText().toString(), passwordEt.getText().toString());
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        user = new User();
    }

    private void login(String userName, String password) {
        if (userName.equals("jere") && password.equals("123")) {
            user.setUserName(userName);
            user.setPassword(password);
            Toast.makeText(MvcLoginActivity.this,
                    user.getUserName() + " Login Successful",
                    Toast.LENGTH_SHORT)
                    .show();
            loginUserName.setText("歡迎登入! " + user.getUserName());
            loginBtn.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MvcLoginActivity.this,
                    "Login Failed",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void logout(){
        user.setUserName("");
        user.setPassword("");
        loginUserName.setText("尚未登入");
        loginBtn.setVisibility(View.VISIBLE);
        logoutBtn.setVisibility(View.GONE);
    }

}
