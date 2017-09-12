package com.example.lijinduo.mydemo.mvp.mvpPresenter;

import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginModel;
import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginPresenter;
import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginView;
import com.example.lijinduo.mydemo.mvp.mvpinterface.OnLoginFinishedListener;
import com.example.lijinduo.mydemo.mvp.mvpmodel.LoginModelImpl;

/**
 * Created by lijinduo on 2017/4/5.
 */

public class LoginPresenterImpl implements LoginPresenter,OnLoginFinishedListener{

    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
        this.loginModel=new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginModel.login(username,password,this);
    }

    @Override
    public void onDestory() {
        loginView=null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.setnavigateToHome();
        }
    }
}
