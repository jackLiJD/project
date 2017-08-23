package com.example.lijinduo.mydemo.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.aidl.AIDLAct;
import com.example.lijinduo.mydemo.animation.AnimationAct;
import com.example.lijinduo.mydemo.retrofit.RetrofitTestAct;
import com.example.lijinduo.mydemo.service.ServiceAct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //返利风账号上传
    //source tree修改代码上传GitHub
    //git修改
    private List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        MainAdapter adapter=new MainAdapter(stringList);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(new MainAdapter.onItemClick() {
            @Override
            public void itemClick(int position) {
                jump(position);
            }
        });
    }

    /**
     * 加载目录
     */
    private void initData() {
        String[] stringArray=getResources().getStringArray(R.array.main_item_title);
        stringList = new ArrayList<String>();
        Collections.addAll(stringList,stringArray);
    }

    /**
     * @param position
     * 跳转目录
     */
    private void jump(int position){
        Intent intent;
        switch (position){
            //动画
            case 0:
                intent=new Intent(this, AnimationAct.class);
                startActivity(intent);
                break;
            //跨进程通讯
            case 1:
                intent=new Intent(this, AIDLAct.class);
                startActivity(intent);
                break;
            //服务模块
            case 2:
                intent=new Intent(this, ServiceAct.class);
                startActivity(intent);
                break;
            //retrofit网络请求框架
            case 3:
                intent=new Intent(this,RetrofitTestAct.class);
                startActivity(intent);
                break;
        }


    }


}
