package com.example.lijinduo.mydemo.Router;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/7
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
@Route(path = "/myPath/routerTest")
public class RouterAct2 extends BaseActivity {
    @Autowired
    String key;

    @Autowired
    int key1;

    //如果没有该字段为null
    @Autowired
    String strNull;
    //如果没有该字段为0
    @Autowired
    int yy;

    @Override
    public void doSmoething() {

    }
    @Autowired
    RouterParcelable routerParcelable = new RouterParcelable();
    @BindView(R.id.back_router)
    Button backRouter;

    private int resultCode=234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_beingb);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
//        Log.d("传递过来的数据", "onCreate: " + key + "==" + key1 + "==" + routerParcelable.title+"=="+strNull+"=="+yy);
    }


    @OnClick(R.id.back_router)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.putExtra("back_str","返回的数据");
        setResult(resultCode,intent);
        finish();
    }
}
