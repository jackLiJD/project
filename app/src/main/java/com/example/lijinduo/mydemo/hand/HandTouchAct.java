package com.example.lijinduo.mydemo.hand;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.AppTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/30
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class HandTouchAct extends BaseActivity {
    @BindView(R.id.in_lin)
    InLin inLin;
    @BindView(R.id.middle_lin)
    LinearLayout middleLin;
    @BindView(R.id.out_lin)
    OutLin outLin;

    private final int REQUEST_CODE_ASK_CALL_PHONE=1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_handtouch);
        ButterKnife.bind(this);

        onCall();
    }
    private void onCall(){
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(HandTouchAct.this, Manifest.permission.SYSTEM_ALERT_WINDOW);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(HandTouchAct.this,new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }else{
                window();
            }
        } else {
            window();
        }
    }

    private void window() {
        AppTool.log("window: ");
//        final Button setview= (Button) findViewById(R.id.setview);
        Button floatingButton = new Button(getApplicationContext());
        floatingButton.setText("button");
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        // flag 设置 Window 属性
        layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级）
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.CENTER;
        final WindowManager windowManager = getWindowManager();
        windowManager.addView(floatingButton, layoutParams);

//        setview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                windowManager.removeView(setview);
//            }
//        });
    }

    @OnClick({R.id.in_lin, R.id.middle_lin, R.id.out_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.in_lin:
                AppTool.log("in");
                break;
            case R.id.middle_lin:
               Toast.makeText(HandTouchAct.this,"middle",Toast.LENGTH_SHORT).show();
                break;
            case R.id.out_lin:
                AppTool.log("out");
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    window();
                } else {
                    // Permission Denied
                    Toast.makeText(HandTouchAct.this, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    public void doSmoething() {

    }
}
