package com.example.lijinduo.mydemo.Router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
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
 * 创建日期：2018/3/6
 * 描述：(重构)
 * 修订历史：
 * 参考链接： https://github.com/alibaba/ARouter
 */
public class RouterAct extends BaseActivity {


    private String TAG="NavigationCallback";

    private int RequestCode = 123;

    @BindView(R.id.router_jump0)
    Button routerJump0;
    @BindView(R.id.router_jump1)
    Button routerJump1;
    @BindView(R.id.router_jump2)
    Button routerJump2;
    @BindView(R.id.router_jump3)
    Button routerJump3;
    private Context context = RouterAct.this;
    String string = "/myPath/routerTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_router);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.router_jump0, R.id.router_jump1, R.id.router_jump2, R.id.router_jump3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.router_jump0:
                Toast.makeText(context, "点击事件", 200).show();
                // 最简单的路由跳转，打开TestActivity
                ARouter.getInstance().build(string).navigation();
                break;
            case R.id.router_jump1:
                //Serializable无效
                RouterSerializable routerSerializable = new RouterSerializable();
                routerSerializable.title = "serializableTitle";
                //Parcelable好使
                RouterParcelable routerParcelable = new RouterParcelable();
                routerParcelable.title = "ParcelableTitle";
                // 其他部分api
                ARouter.getInstance().build(string)
                        .withString("key", "这个是key值")  // 携带跳转参数
                        .withInt("key1", 100)
                        .withSerializable("routerSerializable", routerSerializable)
                        .withParcelable("routerParcelable", routerParcelable)
                        .navigation((Activity) context, RequestCode, new NavigationCallback() {

                            /**
                             * Callback when find the destination.
                             * 找到了
                             * @param postcard meta
                             */
                            @Override
                            public void onFound(Postcard postcard) {

                            }

                            /**
                             * Callback after lose your way.
                             * 找不到了
                             * @param postcard meta
                             */

                            @Override
                            public void onLost(Postcard postcard) {

                            }

                            /**
                             * Callback after navigation.
                             * 跳转完了
                             * @param postcard meta
                             */
                            @Override
                            public void onArrival(Postcard postcard) {

                            }

                            /**
                             * Callback on interrupt.
                             * 被拦截了
                             * @param postcard meta
                             */
                            @Override
                            public void onInterrupt(Postcard postcard) {

                            }
                        });


                break;
            case R.id.router_jump2:
                break;
            case R.id.router_jump3:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 请求code"+requestCode+"+结果code"+resultCode+"数据"+data.getStringExtra("back_str"));


    }
}
