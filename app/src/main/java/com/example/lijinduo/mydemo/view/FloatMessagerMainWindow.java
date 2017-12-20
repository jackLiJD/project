package com.example.lijinduo.mydemo.view;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.hand.OutLin;
import com.example.lijinduo.mydemo.service.ViewService;
import com.example.lijinduo.mydemo.tool.AppManager;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class FloatMessagerMainWindow {
    private Context context;
    private ViewGroup view;
    private WindowManager.LayoutParams mParams = null;
    private WindowManager windowManager = null;
    private static FloatMessagerMainWindow floatMessagerMainWindow;
    private CountDownTimer countDownTimer;
    OutLin imageView;
    public FloatMessagerMainWindow(Context context, ViewGroup view) {
        this.context = context;
        this.view = view;
        showWindow(context);
    }

    public static FloatMessagerMainWindow getFloatMessagerMainWindow(Context context, ViewGroup view) {
        if (floatMessagerMainWindow == null) {
            synchronized (FloatMessagerMainWindow.class) {
                if (floatMessagerMainWindow == null) {
                    floatMessagerMainWindow = new FloatMessagerMainWindow(context, view);
                }
            }
        }
        return floatMessagerMainWindow;
    }

    private void showWindow(final Context context) {
//        if (!isWindowDismiss) {
//            Log.e(TAG, "view is already added here");
//            return;
//        }
//        isWindowDismiss = false;
        if (windowManager == null) {
            windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }

        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        mParams = new WindowManager.LayoutParams();
        mParams.packageName = context.getPackageName();
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.gravity = Gravity.CENTER ;
        mParams.x = screenWidth - dp2px(context, 450);
        mParams.y = screenHeight - dp2px(context, 550);


        imageView = new OutLin(context);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"222",Toast.LENGTH_SHORT).show();
                Log.d("handler", "handleMessage: ");
            }
        });
        windowManager.addView(imageView, mParams);
        countDownTimer = new CountDownTimer(60 * 1000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                handler.sendEmptyMessage(0x123);
            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();
    }

    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.1f);
    }

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                Log.d("0x123", "handleMessage: ");
                imageView.performClick();
            }
        }
    };
}
