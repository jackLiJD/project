package com.example.lijinduo.mydemo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.MyApplication;
import com.example.lijinduo.mydemo.view.CommonPopWindow;
import com.zcx.helper.scale.ScaleScreenHelperFactory;

import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
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
        Log.d("销毁", "onDestroy: ");
        if (this != null) {
            Log.d("销毁", "onDestroy:1111111 ");
        }
        AppManager.getAppManager().removeActivity(this);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            CommonPopWindow.getInstance().closePop();
        }
        return super.onKeyDown(keyCode, event);

    }


}
