package com.example.lijinduo.mydemo.view;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.retrofit.FatherInterFace;
import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.Constant;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class CommonPopWindow {


    public CommonPopWindow(final String  string, final FatherInterFace fatherinterface) {
        View view = LayoutInflater.from(AppManager.getAppManager().currentActivity()).inflate(R.layout.common_popview, null);
        final PopupWindow firstPop = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        firstPop.setOutsideTouchable(false);
        firstPop.setFocusable(true);
        LinearLayout emoney_first_pop_lin_out = (LinearLayout) view.findViewById(R.id.common_out_lin);
        emoney_first_pop_lin_out.getBackground().setAlpha(80);
        Button common_close = (Button) view.findViewById(R.id.common_close);
        common_close.setText("A页面网络请求 收到返回结果时在B页面 B页面弹窗:"+string);
        common_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstPop != null) {
                    Constant.OTHERLOAD=false;
                    fatherinterface.data();
                    firstPop.dismiss();
                }
            }
        });
        firstPop.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
}