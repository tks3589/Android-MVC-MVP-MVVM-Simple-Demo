package com.example.mvcmvpmvvm.mvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mvcmvpmvvm.mvvm.model.User;


/**
 * @author jere
 */
public class LoginViewModel extends ViewModel {
    private User user;
    private MutableLiveData<Integer> loginStatus;

    public LoginViewModel() {
        this.loginStatus = new MutableLiveData<>();
        user = new User();
    }

    public MutableLiveData<Integer> getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatusCode) { // 0:登入成功 1:登入失敗 2:登出
        loginStatus.postValue(loginStatusCode);
    }

    public void login(String userName, String password) {
        if (userName.equals("jere") && password.equals("123")) {
            user.setUserName(userName);
            user.setPassword(password);
            setLoginStatus(0);

        } else {
            setLoginStatus(1);
        }
    }

    public void logout(){
        user.setUserName("");
        user.setPassword("");
        setLoginStatus(2);
    }

    public User getUser() {
        return user;
    }
}
