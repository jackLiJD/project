package com.example.lijinduo.mydemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.MyApplication;
import com.example.lijinduo.mydemo.tool.PermissionsManager;
import com.example.lijinduo.mydemo.tool.PermissionsResultAction;
import com.example.lijinduo.mydemo.view.CommonPopWindow;
import com.zcx.helper.scale.ScaleScreenHelperFactory;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.imid.swipebacklayout.lib.app.SwipeBackPreferenceActivity;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public abstract class BaseActivity extends SwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        overridePendingTransition(R.animator.act_in, R.animator.act_out);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        MyApplication.scaleScreenHelper = ScaleScreenHelperFactory.create(this, 750);
        MyApplication.scaleScreenHelper.loadView((ViewGroup) getWindow().getDecorView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.animator.act_in, R.animator.act_out);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            CommonPopWindow.getInstance().closePop();
        }
        return super.onKeyDown(keyCode, event);

    }
    final int REQUEST_CODE_ASK_CALL_PHONE=123;
    public abstract void doSmoething();

    public void getPermission(String[] permissions){
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, permissions[0]);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }else{
                doSmoething();
            }
        } else {
            doSmoething();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSmoething();
                } else {
                    Toast.makeText(this, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
