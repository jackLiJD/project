package com.example.lijinduo.mydemo.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/9
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ThreadAct extends BaseActivity {
    @BindView(R.id.thread_start)
    Button thread_start;
    @BindView(R.id.thread_stop)
    Button thread_stop;
    Thread thread = null;
    private int countSleep;

    private boolean isStop = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_thread);
        ButterKnife.bind(this);
        Map<String, Integer> map = new HashMap<>();
        Map<String ,String> map1=new Hashtable<>();

        sortInfo();


    }

    private void sortInfo() {
        List<InfoBean> infoBeanList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            InfoBean infoBean = new InfoBean();
            switch (i) {
                case 0:
                    infoBean.setAge(10);
                    infoBean.setName("张三");
                    infoBean.setSex(0);
                    break;
                case 1:
                    infoBean.setAge(10);
                    infoBean.setName("李四");
                    infoBean.setSex(1);
                    break;
                case 2:
                    infoBean.setAge(18);
                    infoBean.setName("王五");
                    infoBean.setSex(0);
                    break;
                case 3:
                    infoBean.setAge(10);
                    infoBean.setName("赵六");
                    infoBean.setSex(1);
                    break;
                case 4:
                    infoBean.setAge(17);
                    infoBean.setName("李一");
                    infoBean.setSex(0);
                    break;
                case 5:
                    infoBean.setAge(19);
                    infoBean.setName("钱二");
                    infoBean.setSex(1);
                    break;
                case 6:
                    infoBean.setAge(25);
                    infoBean.setName("金多");
                    infoBean.setSex(0);
                    break;

            }
            infoBeanList.add(infoBean);
        }

        Collections.sort(infoBeanList, new Comparator<InfoBean>() {
            @Override
            public int compare(InfoBean o1, InfoBean o2) {
                if (o1.getSex() == o2.getSex()) {
                    if (o1.getAge() < o2.getAge()) {
                        return 1;
                    }
                    if (o1.getAge() == o2.getAge()) {
                        return 0;
                    }
                    return -1;
                } else if (o1.getSex() < o2.getSex()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < infoBeanList.size(); i++) {
            Log.d("信息", "姓名: " + infoBeanList.get(i).getName() + "   年龄: " + infoBeanList.get(i).getAge() + "   性别: " + infoBeanList.get(i).getSex());
        }
        //交换位置
//        Collections.swap(infoBeanList,3,5);
//        for (int i = 0; i < infoBeanList.size(); i++) {
//            Log.d("信息swap", "姓名: " + infoBeanList.get(i).getName() + "   年龄: " + infoBeanList.get(i).getAge() + "   性别: " + infoBeanList.get(i).getSex());
//        }

        //以某个点为中心旋转
        Collections.rotate(infoBeanList, 3);
        for (int i = 0; i < infoBeanList.size(); i++) {
            Log.d("信息swap", "姓名: " + infoBeanList.get(i).getName() + "   年龄: " + infoBeanList.get(i).getAge() + "   性别: " + infoBeanList.get(i).getSex());
        }

    }

    @OnClick({R.id.thread_start, R.id.thread_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.thread_start:
                start();
                break;
            case R.id.thread_stop:
                stop();
                break;
        }
    }

    private void start() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStop) {
                    Log.d("threadState", thread.currentThread().getId() + "run: " + countSleep);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        thread.start();
    }

    private void stop() {
        isStop = false;
    }

//    private void start(){
//        thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (isStop&&(!Thread.currentThread().isInterrupted())){
//                    Log.d("threadState", thread.currentThread().getId()+"run: "+countSleep);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        Thread.currentThread().interrupt();
//                    }
//                }
//
//            }
//        });
//
//        thread.start();
//    }
//
//    private void stop(){
//        thread.interrupt();
//
//    }

    @Override
    public void doSmoething() {

    }
}
