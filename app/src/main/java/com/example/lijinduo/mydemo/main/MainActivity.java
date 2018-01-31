package com.example.lijinduo.mydemo.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.adaptation.AdaptationAct;
import com.example.lijinduo.mydemo.aidl.AIDLAct;
import com.example.lijinduo.mydemo.album.MyAlbum;
import com.example.lijinduo.mydemo.animation.AnimationAct;
import com.example.lijinduo.mydemo.backlinear.BackLinearAct;
import com.example.lijinduo.mydemo.being.BeingA;
import com.example.lijinduo.mydemo.book.Book;
import com.example.lijinduo.mydemo.camera.CameraAct;
import com.example.lijinduo.mydemo.electrocardiogram.ElectrocardiogramAct;
import com.example.lijinduo.mydemo.fuzzy.FuzzyImgAct;
import com.example.lijinduo.mydemo.hand.HandTouchAct;
import com.example.lijinduo.mydemo.kotlin.Kotlin;
import com.example.lijinduo.mydemo.memory.MemoryAct;
import com.example.lijinduo.mydemo.mvp.mvpactivity.MvpActivity;
import com.example.lijinduo.mydemo.mvvm.MvvmAct;
import com.example.lijinduo.mydemo.mymap.MyMapAct;
import com.example.lijinduo.mydemo.permission.PermissionAct;
import com.example.lijinduo.mydemo.poppay.PopPayAct;
import com.example.lijinduo.mydemo.push.PushActivity;
import com.example.lijinduo.mydemo.redbag.RedBagAct;
import com.example.lijinduo.mydemo.retrofit.RetrofitTestAct;
import com.example.lijinduo.mydemo.service.ServiceAct;
import com.example.lijinduo.mydemo.surfaceview.SurfaceAct;
import com.example.lijinduo.mydemo.thread.ThreadAct;
import com.example.lijinduo.mydemo.toast.ToastAct;
import com.example.lijinduo.mydemo.todaynews.TodayNewsAct;
import com.example.lijinduo.mydemo.view.CustomVerticalCenterSpan;
import com.example.lijinduo.mydemo.vr.VRAct;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_list)
    RecyclerView mainList;
    @BindView(R.id.inclue_title)
    LinearLayout inclueTitle;
    private List<String> stringList;
    private Context context = MainActivity.this;
    private LinearLayout inclue_title;
    MainAdapter adapter;
    private TextView xuanfutitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Kotlin().main();
        ButterKnife.bind(this);
        initData();
        inclue_title = (LinearLayout) findViewById(R.id.inclue_title);
        xuanfutitle = (TextView) findViewById(R.id.xuanfutitle);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new MainAdapter(stringList, context);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(new MainAdapter.onItemClick() {
            @Override
            public void itemClick(int position) {
                jump(position);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //这个5这个参数 如果换成inclue_title.getMeasuredHeight() 就有问题 他这个取值 咋取的
                View stickyInfoView = recyclerView.findChildViewUnder(inclue_title.getMeasuredWidth(), 20);
                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    xuanfutitle.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                View transInfoView = recyclerView.findChildViewUnder(
                        inclue_title.getMeasuredWidth(), inclue_title.getMeasuredHeight());
                if (transInfoView.getTop() > 0) {
                    inclue_title.setTranslationY(transInfoView.getTop() - inclue_title.getMeasuredHeight());
                } else {
                    inclue_title.setTranslationY(0);
                }
                super.onScrolled(recyclerView, dx, dy);
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
        Intent intent = null;
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
                intent = new Intent(this, BeingA.class);
                break;
            case 10:
                intent = new Intent(this, ToastAct.class);
                break;
            case 11:
                intent = new Intent(this, MyAlbum.class);
                break;
            case 12:
                intent = new Intent(this, Book.class);
                break;
            case 13:
                intent = new Intent(this, VRAct.class);
                break;
            case 14:
                intent = new Intent(this, ThreadAct.class);
                break;
            case 15:
                intent = new Intent(this, BackLinearAct.class);
                break;
            case 16:
                intent = new Intent(this, MyMapAct.class);
                break;
            case 17:
                intent = new Intent(this, ElectrocardiogramAct.class);
                break;
            case 18:
                intent = new Intent(this, RedBagAct.class);
                break;
            case 19:
                intent = new Intent(this, FuzzyImgAct.class);
                break;
            case 20:
                intent = new Intent(this, PopPayAct.class);
                break;
            case 21:
                intent = new Intent(this, SurfaceAct.class);
                break;
            case 22:
                intent = new Intent(this, CameraAct.class);
                break;
            case 23:
                intent = new Intent(this, AdaptationAct.class);
                break;
            case 24:
                intent = new Intent(this, MemoryAct.class);
                break;
            case 25:
                intent = new Intent(this, TodayNewsAct.class);
                break;

        }
        startActivity(intent);
    }


}
