package com.example.lijinduo.mydemo.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/22
 * 描述：(重构)
 * 修订历史：
 * 参考链接：http://www.jianshu.com/p/eeb2bd59853f
 */
public class ServiceAct extends BaseActivity implements View.OnClickListener {
    Button start_service, stop_servie, bind_service, unbind_service, start_intentservice;
    Button do_someting,open_something;
    //一开始，并没有和Service绑定.这个参数是用来显示绑定状态
    boolean mbound=false;

    private MyBindService.MyBinder myBindService;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBindService= (MyBindService.MyBinder) service;
//            myBindService.doSomething();
//            myBindService.openString();
            mbound = true; //true说明是绑定状态
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mbound=false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_service);
        initView();
    }

    private void initView() {
        start_service = (Button) findViewById(R.id.start_service);
        stop_servie = (Button) findViewById(R.id.stop_servie);
        bind_service = (Button) findViewById(R.id.bind_service);
        unbind_service = (Button) findViewById(R.id.unbind_service);
        start_intentservice = (Button) findViewById(R.id.start_intentservice);
        do_someting = (Button) findViewById(R.id.do_someting);
        open_something= (Button) findViewById(R.id.open_something);
        start_service.setOnClickListener(this);
        stop_servie.setOnClickListener(this);
        bind_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);
        start_intentservice.setOnClickListener(this);
        do_someting.setOnClickListener(this);
        open_something.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent inetnt = new Intent(this, ServiceTest.class);
        Intent intentService = new Intent(this, MyIntentService.class);
        Intent bindIntent = new Intent(this, MyBindService.class);
        switch (v.getId()) {
            case R.id.start_service:
                Log.d("线程id", "onClick: " + Thread.currentThread().getId());
                startService(inetnt);
                break;
            case R.id.stop_servie:
                stopService(inetnt);
                break;
            case R.id.bind_service:

                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                //如果和Service是绑定的状态，就解除绑定。
                if(mbound){
                    unbindService(connection);
                    mbound=false;
                }
                break;
            case R.id.start_intentservice:
                startService(intentService);
                break;
            case R.id.do_someting:
                //确保服务已经启用
                if(mbound) {
                    myBindService.doSomething();
                }
                break;
            case R.id.open_something:
                if(mbound) {
                myBindService.openString();
                }
                break;

        }
    }
    @Override
    public void doSmoething() {

    }
}
