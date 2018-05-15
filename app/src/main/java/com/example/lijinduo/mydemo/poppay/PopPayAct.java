package com.example.lijinduo.mydemo.poppay;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
 * 创建日期：2017/12/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PopPayAct extends BaseActivity {
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_poppay);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.button8, R.id.button9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button8:
                PayPop payPop=new PayPop(PopPayAct.this, 0, new PayPop.NoticeDone() {
                    @Override
                    public void pwd(String pwd) {

                    }

                    @Override
                    public void pwdNew(String pwd1, String pwd2) {
                        Toast.makeText(PopPayAct.this,pwd1+"／"+pwd2,Toast.LENGTH_SHORT).show();
                    }

                });
                break;
            case R.id.button9:
                PayPop payPop2=new PayPop(PopPayAct.this, 1, new PayPop.NoticeDone() {
                    @Override
                    public void pwd(String pwd) {
                        Toast.makeText(PopPayAct.this,pwd,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void pwdNew(String pwd1, String pwd2) {

                    }


                });
                break;
        }
    }
    @Override
    public void doSmoething() {

    }
}
