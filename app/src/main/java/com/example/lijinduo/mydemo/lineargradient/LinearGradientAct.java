package com.example.lijinduo.mydemo.lineargradient;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
 * 创建日期：2018/3/26
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class LinearGradientAct extends BaseActivity {
    @BindView(R.id.tv_linear_gradient)
    LinearGradientTextView tvLinearGradient;
    @BindView(R.id.text_view_play)
    TextViewPlay textViewPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_linear_gradient);
        int pid = android.os.Process.myPid();
        Log.e("进程id","LinearGradientPid====" + pid );
        ButterKnife.bind(this);
        textViewPlay.setText("爱就一个字，我只说一次");
        testInstanceof();
        Log.d(TAG,   Uri.encode("1111+qqqq"));
    }


    private String TAG = "testInstanceof";

    @OnClick(R.id.text_view_play)
    public void onViewClicked() {
        Toast.makeText(this, "33333", Toast.LENGTH_SHORT).show();
        PopPrompt popPrompt=new PopPrompt(LinearGradientAct.this,textViewPlay);

    }

    private class Father {
        public String cooking() {
            return "cook";
        }

    }

    private class Son extends Father {
        public String eating() {
            return "eat";
        }
    }


    private void testInstanceof() {
        Father father = new Father();
        Log.d(TAG, "1testInstanceof: " + (father instanceof Father));
        Son son = new Son();
        Log.d(TAG, "2testInstanceof: " + (son instanceof Father));
        Log.d(TAG, "3testInstanceof: " + (father instanceof Son));
        Log.d(TAG, "4testInstanceof: " + son.cooking());
        Son son1 = new Son();
        Father f = son1;
        Log.d(TAG, "5testInstanceof: " + f.cooking());


    }


}
