package com.example.lijinduo.mydemo.adaptation;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.NoScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/25
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class AdaptationAct extends BaseActivity {
    @BindView(R.id.in_scroll)
    ScrollView inScroll;
    @BindView(R.id.out_scroll)
    NoScrollView outScroll;
    @BindView(R.id.transition_view)
    ImageView transitionView;
    private int screenHeight = 0;
    private int topY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adaptation);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
//        screenHeight = measureHeight();
//        //设置第一个滑动布局
//        ViewGroup.LayoutParams view = (ViewGroup.LayoutParams) inScroll.getLayoutParams();
//        view.height = screenHeight - getStatusBarHeight();
//        inScroll.setLayoutParams(view);
        inScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                int y = 0;
                int offsety = 0;
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        topY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        y = (int) event.getRawY();
                        offsety = y - topY;
                        outScroll.scrollBy(0, offsety / 4);
                        transitionView.setTranslationY(offsety);
                        break;
                }


                return false;
            }
        });


    }


    public int measureHeight() {
        WindowManager wManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
