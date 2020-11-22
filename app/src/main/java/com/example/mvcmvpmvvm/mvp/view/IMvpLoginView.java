package com.example.mvcmvpmvvm.mvp.view;

/**
 * @author jere
 */
public interface IMvpLoginView {

    void onLoginResult(Boolean isLoginSuccess);

    void onLogoutResult(); //TM多個功能就多個接口
}
