package com.example.mvcmvpmvvm.mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvcmvpmvvm.R;
import com.example.mvcmvpmvvm.mvvm.viewmodel.LoginViewModel;

/**
 * @author jere
 */
public class MvvmLoginActivity extends AppCompatActivity {
    private EditText userNameEt,passwordEt;
    private Button loginBtn,logoutBtn;
    private TextView loginUserName;

    private LoginViewModel loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_login);

        userNameEt = findViewById(R.id.user_name_et);
        passwordEt = findViewById(R.id.password_et);
        loginUserName = findViewById(R.id.login_username);
        loginBtn = findViewById(R.id.login_btn);
        logoutBtn = findViewById(R.id.logout_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginVM.login(userNameEt.getText().toString(), passwordEt.getText().toString());
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginVM.logout();
            }
        });

        loginVM = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginVM.getLoginStatus().observe(this, loginObserver); //監聽登出入狀態
    }

    private Observer<Integer> loginObserver = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer loginStatusCode) {
            if (loginStatusCode == 0) { //登入成功
                String username = loginVM.getUser().getUserName();
                Toast.makeText(MvvmLoginActivity.this,
                        username + " Login Successful",
                        Toast.LENGTH_SHORT)
                        .show();
                loginUserName.setText("歡迎登入! " + username);
                loginBtn.setVisibility(View.GONE);
                logoutBtn.setVisibility(View.VISIBLE);
            }
            else if(loginStatusCode == 1){ //登入失敗
                Toast.makeText(MvvmLoginActivity.this,
                        "Login Failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else { //登出
                loginUserName.setText("尚未登入");
                loginBtn.setVisibility(View.VISIBLE);
                logoutBtn.setVisibility(View.GONE);
            }
        }
    };
}
