package com.example.lijinduo.mydemo.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.aidl.AIDLAct;
import com.example.lijinduo.mydemo.album.MyAlbum;
import com.example.lijinduo.mydemo.animation.AnimationAct;
import com.example.lijinduo.mydemo.backlinear.BackLinearAct;
import com.example.lijinduo.mydemo.being.BeingA;
import com.example.lijinduo.mydemo.book.Book;
import com.example.lijinduo.mydemo.hand.HandTouchAct;
import com.example.lijinduo.mydemo.main.MainAdapter;
import com.example.lijinduo.mydemo.mvp.mvpactivity.MvpActivity;
import com.example.lijinduo.mydemo.mvvm.MvvmAct;
import com.example.lijinduo.mydemo.permission.PermissionAct;
import com.example.lijinduo.mydemo.push.PushActivity;
import com.example.lijinduo.mydemo.retrofit.RetrofitTestAct;
import com.example.lijinduo.mydemo.service.ServiceAct;
import com.example.lijinduo.mydemo.thread.ThreadAct;
import com.example.lijinduo.mydemo.toast.ToastAct;
import com.example.lijinduo.mydemo.vr.VRAct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity {

    //返利风账号上传
    //source tree修改代码上传GitHub
    //git修改
    private List<String> stringList;

    private  String str="李金舵mast分之提交";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        MainAdapter adapter = new MainAdapter(stringList);
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
        String[] stringArray = getResources().getStringArray(R.array.main_item_title);
        stringList = new ArrayList<>(Arrays.asList(stringArray));
//        Collections.addAll(stringList, stringArray);
    }

    /**
     * @param position 跳转目录
     */
    private void jump(int position) {
        Intent intent=null;
        switch (position) {
            //动画
            case 0:
                intent = new Intent(this, AnimationAct.class);
                break;
            //跨进程通讯
            case 1:
                intent = new Intent(this, AIDLAct.class);
                break;
            //服务模块
            case 2:
                intent = new Intent(this, ServiceAct.class);
                break;
            //retrofit网络请求框架
            case 3:
                intent = new Intent(this, RetrofitTestAct.class);
                break;
            //mvvm
            case 4:
                intent = new Intent(this, MvvmAct.class);
                break;
            //mvp
            case 5:
                intent = new Intent(this, MvpActivity.class);
                break;
            //开启推送
            case 6:
                intent = new Intent(this, PushActivity.class);
                break;
            //权限测试
            case 7:
                intent = new Intent(this, PermissionAct.class);
                break;
            case 8:
                intent = new Intent(this, HandTouchAct.class);
                break;
            case 9:
                intent=new Intent(this, BeingA.class);
                break;
            case 10:
                intent=new Intent(this, ToastAct.class);
                break;
            case 11:
                intent=new Intent(this, MyAlbum.class);
                break;
            case 12:
                intent=new Intent(this, Book.class);
                break;
            case 13:
                intent=new Intent(this, VRAct.class);
                break;
            case 14:
                intent=new Intent(this, ThreadAct.class);
                break;
            case 15:
                intent=new Intent(this, BackLinearAct.class);
                break;
        }

        startActivity(intent);
    }


}
