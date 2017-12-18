package com.example.lijinduo.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Rest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rest);

        int a=Integer.parseInt(getIntent().getStringExtra("ed1"));
        int b=Integer.parseInt(getIntent().getStringExtra("ed2"));
        int c=Integer.parseInt(getIntent().getStringExtra("ed3"));
        TextView jieguo = (TextView) findViewById(R.id.jieguo);
        jieguo.setText((a*a*a+b*b*b+c*c*c)+"");

    }
}
