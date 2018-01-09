package com.example.lijinduo.mydemo.todaynews;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/8
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class TodayNewsAct extends BaseActivity {
    @BindView(R.id.head_recycler)
    RecyclerView headRecycler;
    @BindView(R.id.viewpage)
    ClipViewPager viewpage;
    @BindView(R.id.viewpage_lin)
    LinearLayout viewpageLin;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    private List<String> stringList;
    private NewsHeadAdapter adapter;
    private Context context = TodayNewsAct.this;
    LinearLayoutManager linearLayoutManager;
    private List<NewsBean> lists = new ArrayList<>();
    ScalePageTransformer scalePageTransformer;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_today_news);
        ButterKnife.bind(this);
        data();
        headView();
        Log.d("线程id", "onCreate: " + Thread.currentThread().getId());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("线程id", "onCreate: " + Thread.currentThread().getId());
            }
        });
        thread.start();

    }

    /**
     * 头部数据
     */
    private void data() {
        String[] stringArray = getResources().getStringArray(R.array.main_item_title);
        stringList = new ArrayList<>(Arrays.asList(stringArray));
        for (int i = 0; i < stringList.size(); i++) {
            NewsBean newsBean = new NewsBean();
            Log.d("数据", stringList.get(i).toString());
            newsBean.setStr(stringList.get(i).toString());
            if (i == 0) {
                newsBean.setB(true);
            } else {
                newsBean.setB(false);
            }
            lists.add(newsBean);
            fragmentList.add(new Fragment1());
        }
        scalePageTransformer=new ScalePageTransformer(1);
        viewpage.setPageTransformer(true,scalePageTransformer );
        viewpageLin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpage.dispatchTouchEvent(event);
            }
        });

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragmentList, lists);
        viewpage.setAdapter(adapter);
        viewpage.setOffscreenPageLimit(3);
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("选中", "onPageSelected: " + position);
                TAB(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 头部view
     */
    private void headView() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRecycler.setLayoutManager(linearLayoutManager);
        adapter = new NewsHeadAdapter(context, lists);
        headRecycler.setAdapter(adapter);
        adapter.setNotic(new NewsHeadAdapter.Notic() {
            @Override
            public void notic(int position) {
                TAB(position);
                viewpage.setCurrentItem(position);
            }
        });
    }


    /**
     * 切换选项卡
     */
    private void TAB(int position) {
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();
        int middleItem = (firstItem + lastItem) / 2;
        //改变颜色和字体大小放在前面
        for (int i = 0; i < lists.size(); i++) {
            lists.get(i).setB(false);
            if (i == position) {
                lists.get(i).setB(true);
            }
        }
        adapter.notifyDataSetChanged();
        //一共七项  大于能移动右边移动 不能引动改变颜色   小于反之
        if (position > middleItem) {
            if (position - middleItem + lastItem > stringList.size()) {
                headRecycler.smoothScrollToPosition(stringList.size() - 1);
            } else {
                headRecycler.smoothScrollToPosition(position - middleItem + lastItem);
            }
        } else if (position < middleItem) {
            if (firstItem - (middleItem - position) < 0) {
                headRecycler.smoothScrollToPosition(0);
            } else {
                headRecycler.smoothScrollToPosition(firstItem - (middleItem - position));
            }
        }
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                scalePageTransformer=new ScalePageTransformer(1);
                viewpage.setPageTransformer(true,scalePageTransformer );
                break;
            case R.id.btn2:
                scalePageTransformer=new ScalePageTransformer(2);
                viewpage.setPageTransformer(true,scalePageTransformer );
                break;
            case R.id.btn3:
                scalePageTransformer=new ScalePageTransformer(3);
                viewpage.setPageTransformer(true,scalePageTransformer );
                break;
        }
    }
}
