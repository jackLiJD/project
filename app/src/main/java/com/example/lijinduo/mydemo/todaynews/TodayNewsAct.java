package com.example.lijinduo.mydemo.todaynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.IndicatorView;
import com.example.lijinduo.mydemo.view.NoTouchViewpage;

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
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.viewpage1)
    NoTouchViewpage viewpage1;

    private List<String> stringList;
    private NewsHeadAdapter adapter;
    private Context context = TodayNewsAct.this;
    LinearLayoutManager linearLayoutManager;
    private List<NewsBean> lists = new ArrayList<>();
    ScalePageTransformer scalePageTransformer;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<Fragment> fragmentList1 = new ArrayList<>();
    FragAdapter adaptermm;
    FragAdapter adapteryy;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
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
                fragmentList1.add(new Fragment2());

                adapteryy.notifyDataSetChanged();
                adaptermm.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_today_news);
        ButterKnife.bind(this);
        data();
        headView();
        bottom();
        handler.sendEmptyMessageDelayed(123,200);
    }

    private void bottom() {
        scalePageTransformer = new ScalePageTransformer(3);
        viewpage1.setPageTransformer(true, scalePageTransformer);
         adapteryy = new FragAdapter(getSupportFragmentManager(), fragmentList1, lists);
        viewpage1.setOffscreenPageLimit(fragmentList1.size());
        viewpage1.setAdapter(adapteryy);

    }

    /**
     * 头部数据
     */
    private void data() {
        String[] stringArray = getResources().getStringArray(R.array.main_item_title);
        stringList = new ArrayList<>(Arrays.asList(stringArray));
//        for (int i = 0; i < stringList.size(); i++) {
//            NewsBean newsBean = new NewsBean();
//            Log.d("数据", stringList.get(i).toString());
//            newsBean.setStr(stringList.get(i).toString());
//            if (i == 0) {
//                newsBean.setB(true);
//            } else {
//                newsBean.setB(false);
//            }
//            lists.add(newsBean);
//            fragmentList.add(new Fragment1());
//            fragmentList1.add(new Fragment2());
//        }
        scalePageTransformer = new ScalePageTransformer(1);
        viewpage.setPageTransformer(true, scalePageTransformer);
        viewpageLin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpage.dispatchTouchEvent(event);
            }
        });
         adaptermm = new FragAdapter(getSupportFragmentManager(), fragmentList, lists);
        viewpage.setAdapter(adaptermm);
//        for (int i = 0; i < stringList.size(); i++) {
//            NewsBean newsBean = new NewsBean();
//            Log.d("数据", stringList.get(i).toString());
//            newsBean.setStr(stringList.get(i).toString());
//            if (i == 0) {
//                newsBean.setB(true);
//            } else {
//                newsBean.setB(false);
//            }
//            lists.add(newsBean);
//            fragmentList.add(new Fragment1());
//            fragmentList1.add(new Fragment2());
//        }


        viewpage.setOffscreenPageLimit(3);
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TAB(position);
                viewpage1.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        for (int i = 0; i < stringList.size(); i++) {
//            NewsBean newsBean = new NewsBean();
//            Log.d("数据", stringList.get(i).toString());
//            newsBean.setStr(stringList.get(i).toString());
//            if (i == 0) {
//                newsBean.setB(true);
//            } else {
//                newsBean.setB(false);
//            }
//            lists.add(newsBean);
//            fragmentList.add(new Fragment1());
//            fragmentList1.add(new Fragment2());
//        }
//        adapter.notifyDataSetChanged();
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

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                viewpageLin.setGravity(Gravity.CENTER);
                scalePageTransformer = new ScalePageTransformer(1);
                viewpage.setPageTransformer(true, scalePageTransformer);
                break;
            case R.id.btn2:
                viewpageLin.setGravity(Gravity.CENTER);
                scalePageTransformer = new ScalePageTransformer(2);
                viewpage.setPageTransformer(true, scalePageTransformer);
                break;
            case R.id.btn3:
                viewpageLin.setGravity(Gravity.CENTER);
                scalePageTransformer = new ScalePageTransformer(3);
                viewpage.setPageTransformer(true, scalePageTransformer);
                break;
            case R.id.btn4:
                viewpageLin.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;


        }
    }


}
