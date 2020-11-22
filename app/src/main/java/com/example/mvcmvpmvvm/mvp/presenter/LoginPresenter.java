package com.example.mvcmvpmvvm.mvp.presenter;

import com.example.mvcmvpmvvm.mvp.model.User;
import com.example.mvcmvpmvvm.mvp.view.IMvpLoginView;

/**
 * @author jere
 */
public class LoginPresenter{ // model & view的橋樑
    private IMvpLoginView iMvpLoginView;
    private User user;
    public LoginPresenter(IMvpLoginView iMvpLoginView) {
        this.iMvpLoginView = iMvpLoginView;
        this.user = new User();
    }

    public void login(String userName,String password) {
        if (userName.equals("jere") && password.equals("123")) {
            user.setUserName(userName);
            user.setPassword(password);
            iMvpLoginView.onLoginResult(true);
        }else
            iMvpLoginView.onLoginResult(false);

    }

    public void logout(){
        user.setUserName("");
        user.setPassword("");
        iMvpLoginView.onLogoutResult();
    }

    public User getUser(){
        return user;
    }


}
