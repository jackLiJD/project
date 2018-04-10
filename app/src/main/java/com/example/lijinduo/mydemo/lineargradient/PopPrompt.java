package com.example.lijinduo.mydemo.lineargradient;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.lijinduo.mydemo.R.color.ff0000;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/30
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PopPrompt {
    private Context context;
    private View view;
    private PopupWindow firstPop;
    private View locationView;

    public PopPrompt(Context context, View locationView) {
        this.context = context;
        this.locationView = locationView;
        inflate();
    }

    private void inflate() {
        view = LayoutInflater.from(context).inflate(R.layout.pop_prompt_redbag, null);
        firstPop = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        firstPop.setFocusable(false);
        firstPop.setOutsideTouchable(false);
        firstPop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        LinearLayout touch=view.findViewById(R.id.touch);
        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "11111", Toast.LENGTH_SHORT).show();
            }
        });
        Button close=view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "22222", Toast.LENGTH_SHORT).show();
                firstPop.dismiss();
            }
        });
        int[] location = new int[2];
        locationView.getLocationOnScreen(location);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int promptHeight =view.getMeasuredHeight();
        firstPop.showAtLocation(locationView, Gravity.NO_GRAVITY,0, location[1] - promptHeight);
    }

}
