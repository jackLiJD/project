package com.example.lijinduo.mydemo.poppay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.MyApplication;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PayPop implements View.OnClickListener {
    private PopupWindow popView;
    /**
     * 正在输入的密码 inputStr
     * 已经输入等待校验的密码 firstStr
     */
    private String inputStr="", firstStr="";

    /**
     * 输入框显示的样式
     */
    private ImageView pop_paypasswordimg;
    /**
     * @param context
     * @param type 0为第一次设置  1为已经设置过了
     */
    private int type;

    /**
     * 错误提示信息
     */
    private TextView pop_pay_error;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //清除
            if (msg.what == 0x123) {
                inputStr = "";
                changeBackImage();
            }
        }
    };

    private NoticeDone noticeDone;

    public PayPop(Context context, int type, NoticeDone noticeDone) {
        this.type = type;
        this.noticeDone = noticeDone;
        initView(context);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_pay, null);
        MyApplication.scaleScreenHelper.loadView((ViewGroup) view);
        popView = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popView.setOutsideTouchable(false);
        popView.setFocusable(true);
        //背景色
        LinearLayout pop_pay_out = (LinearLayout) view.findViewById(R.id.pop_pay_out);
        pop_pay_out.getBackground().setAlpha(80);
        //标题
        TextView pop_pay_title = (TextView) view.findViewById(R.id.pop_pay_title);
        if (type == 0) {
            pop_pay_title.setText("为了您账户安全，请设置交易密码");
        } else {
            pop_pay_title.setText("请输入交易密码");
        }
        //关闭
        view.findViewById(R.id.pop_pay_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClosePop();
            }
        });
        //键盘0-9 clear
        TextView pop_pay_0 = (TextView) view.findViewById(R.id.pop_pay_0);
        TextView pop_pay_1 = (TextView) view.findViewById(R.id.pop_pay_1);
        TextView pop_pay_2 = (TextView) view.findViewById(R.id.pop_pay_2);
        TextView pop_pay_3 = (TextView) view.findViewById(R.id.pop_pay_3);
        TextView pop_pay_4 = (TextView) view.findViewById(R.id.pop_pay_4);
        TextView pop_pay_5 = (TextView) view.findViewById(R.id.pop_pay_5);
        TextView pop_pay_6 = (TextView) view.findViewById(R.id.pop_pay_6);
        TextView pop_pay_7 = (TextView) view.findViewById(R.id.pop_pay_7);
        TextView pop_pay_8 = (TextView) view.findViewById(R.id.pop_pay_8);
        TextView pop_pay_9 = (TextView) view.findViewById(R.id.pop_pay_9);
        LinearLayout pop_pay_clear = (LinearLayout) view.findViewById(R.id.pop_pay_clear);
        pop_paypasswordimg = (ImageView) view.findViewById(R.id.pop_paypasswordimg);
        pop_pay_error = (TextView) view.findViewById(R.id.pop_pay_error);
        pop_pay_0.setOnClickListener(this);
        pop_pay_1.setOnClickListener(this);
        pop_pay_2.setOnClickListener(this);
        pop_pay_3.setOnClickListener(this);
        pop_pay_4.setOnClickListener(this);
        pop_pay_5.setOnClickListener(this);
        pop_pay_6.setOnClickListener(this);
        pop_pay_7.setOnClickListener(this);
        pop_pay_8.setOnClickListener(this);
        pop_pay_9.setOnClickListener(this);
        pop_pay_clear.setOnClickListener(this);
        popView.setAnimationStyle(R.style.mypopwindow_anim_style);
        popView.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_pay_0:
                addStr("0");
                break;
            case R.id.pop_pay_1:
                addStr("1");
                break;
            case R.id.pop_pay_2:
                addStr("2");
                break;
            case R.id.pop_pay_3:
                addStr("3");
                break;
            case R.id.pop_pay_4:
                addStr("4");
                break;
            case R.id.pop_pay_5:
                addStr("5");
                break;
            case R.id.pop_pay_6:
                addStr("6");
                break;
            case R.id.pop_pay_7:
                addStr("7");
                break;
            case R.id.pop_pay_8:
                addStr("8");
                break;
            case R.id.pop_pay_9:
                addStr("9");
                break;
            case R.id.pop_pay_clear:
                reduceStr();
                break;
        }
    }

    /**
     * @param str 添加字符串
     */
    private void addStr(String str) {
        pop_pay_error.setText("");
        if (inputStr.length() == 6) {
            return;
        }
        inputStr = inputStr + str;
        changeBackImage();

        if (inputStr.length() == 6) {
            if (type == 0) {
                if (firstStr == null || firstStr.equals("")) {
                    firstStr = inputStr;
                    clearInput();
                } else {
                    noticeDone.pwdNew(firstStr, inputStr);
                }
            } else {
                noticeDone.pwd(inputStr);
            }

        }
    }

    /**
     * 橡皮键
     */
    private void reduceStr() {
        if (inputStr.length() == 0||inputStr.length() == 6) {
            return;
        }
        if (inputStr.length() > 0) {
            inputStr = inputStr.substring(0, inputStr.length() - 1);
            changeBackImage();
        }
    }

    /**
     * 清除第一次输入的内容
     */
    private void clearInput() {
        handler.sendEmptyMessageDelayed(0x123, 300);
    }

    /**
     * 根据字符串的长度设置显示的背景
     */
    private void changeBackImage() {

        switch (inputStr.length()) {
            case 0:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_0);
                break;
            case 1:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_1);
                break;
            case 2:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_2);
                break;
            case 3:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_3);
                break;
            case 4:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_4);
                break;
            case 5:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_5);
                break;
            case 6:
                pop_paypasswordimg.setBackgroundResource(R.mipmap.password_6);
                break;
        }

    }

    /**
     * @param msg
     * 错误信息
     */
    public void setErrorMsg(String msg) {
        pop_pay_error.setText(msg);
        firstStr = "";
        inputStr = "";
        pop_paypasswordimg.setBackgroundResource(R.mipmap.password_error);
    }

    /**
     * 关闭弹窗
     */
    public void setClosePop() {
        if (popView != null) {
            popView.dismiss();
            popView = null;
        }
    }


    public interface NoticeDone {

        /**
         * @param pwd 输入的密码
         */
        void pwd(String pwd);

        /**
         * @param pwd1 第一次输入的密码
         * @param pwd2 第二次输入的密码
         */
        void pwdNew(String pwd1, String pwd2);

    }


}
