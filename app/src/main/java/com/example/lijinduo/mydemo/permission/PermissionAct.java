package com.example.lijinduo.mydemo.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

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
public class PermissionAct extends BaseActivity {
    @BindView(R.id.call)
    Button call;

    private final int REQUEST_CODE_ASK_CALL_PHONE=1234;

    private Context mContext=PermissionAct.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_permission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.call)
    public void onViewClicked() {
        onCall();
    }

    private void onCall(){
            if (Build.VERSION.SDK_INT >= 23) {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);
                if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) mContext,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_ASK_CALL_PHONE);
                    return;
                }else{
                    //上面已经写好的拨号方法
                    callDirectly();
                }
            } else {
                //上面已经写好的拨号方法
                callDirectly();
            }
    }

    /**
     * 拨打功能
     */
    private void callDirectly(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" +"15868186914"));
        startActivity(intent);
    }

    /**
     * @param requestCode 请求code
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    callDirectly();
                } else {
                    // Permission Denied
                    Toast.makeText(mContext, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
