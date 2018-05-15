package com.example.lijinduo.mydemo.mvp.mvpactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.mvp.mvpPresenter.LoginPresenterImpl;
import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginPresenter;
import com.example.lijinduo.mydemo.mvp.mvpinterface.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lijinduo on 2017/4/5.
 */

//实现一个view接口
public class MvpActivity extends Activity implements LoginView {
    @BindView(R.id.uesrname)
    EditText uesrname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.mvp_progress)
    ProgressBar mvpProgress;
    @BindView(R.id.uesrname_input)
    TextInputLayout uesrnameInput;
    @BindView(R.id.password_input)
    TextInputLayout passwordInput;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mvp);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImpl(MvpActivity.this);
    }

    @Override
    public void showProgress() {

        mvpProgress.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        mvpProgress.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        uesrnameInput.setEnabled(true);
        uesrnameInput.setError("账号为空");
    }

    @Override
    public void setPasswordError() {
        passwordInput.setEnabled(true);
        passwordInput.setError("密码为空");

    }

    @Override
    public void setnavigateToHome() {
        uesrnameInput.setEnabled(false);
        passwordInput.setEnabled(false);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOtherthing() {

    }


    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }

    @OnClick({R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                presenter.validateCredentials(uesrname.getText().toString(), password.getText().toString());
                break;
        }
    }

}
