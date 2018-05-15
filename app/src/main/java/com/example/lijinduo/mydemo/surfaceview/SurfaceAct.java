package com.example.lijinduo.mydemo.surfaceview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.Rest;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class SurfaceAct extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_surface);
        final EditText editText1= (EditText) findViewById(R.id.ed1);
        final EditText editText2= (EditText) findViewById(R.id.ed2);
        final EditText editText3= (EditText) findViewById(R.id.ed3);


        Button jump= (Button) findViewById(R.id.jump);

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().equals("")||editText2.getText().toString().equals("")||editText3.getText().toString().equals("")) {
      return;
                }
                Log.d("值", "onClick: "+editText1.getText().toString());
                Intent intent=new Intent(SurfaceAct.this,Rest.class);
                intent.putExtra("ed1",editText1.getText().toString());
                intent.putExtra("ed2",editText2.getText().toString());
                intent.putExtra("ed3",editText3.getText().toString());
                startActivity(intent);
            }
        });



    }
    @Override
    public void doSmoething() {

    }
}
