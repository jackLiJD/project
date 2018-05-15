package com.example.lijinduo.mydemo.rxpermission;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RxPermissionsAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rxpermissions);
        requestPermissions();
    }

    private void requestPermissions() {
        new RxPermissions(RxPermissionsAct.this).request(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            goToMain();
                        } else {
                            requestFailure();
                        }
                    }
                });
    }


    private void goToMain(){
        ToastUtils.showToast("ojbk");
    }

    private void requestFailure(){
        ToastUtils.showToast("获取失败");
    }


}
