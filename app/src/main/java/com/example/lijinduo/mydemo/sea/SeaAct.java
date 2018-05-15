package com.example.lijinduo.mydemo.sea;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/17
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class SeaAct extends BaseActivity {
    @BindView(R.id.sea_positon)
    EditText seaPositon;
    @BindView(R.id.sea_go)
    TextView seaGo;
    @BindView(R.id.sea_jump1)
    TextView seaJump1;
    @BindView(R.id.sea_jump2)
    TextView seaJump2;
    @BindView(R.id.sea_list)
    RecyclerView seaList;
    @BindView(R.id.sea_man)
    SeaManView seaMan;
    private Context context = SeaAct.this;
    private LinearLayoutManager linearManager;
    private int height1;
    private boolean canTouch;
    SeaAdapter adapter;
    private List<Integer> listsLocation=new ArrayList<>();
    private int screenWidth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sea);
        ButterKnife.bind(this);
        screenWidth=getWindowManager().getDefaultDisplay().getWidth();
        initView();
        seaMan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (canTouch) {
                    return seaList.dispatchTouchEvent(event);
                }
                return true;
            }
        });
    }

    private void initView() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add("坐标" + i);
        }
        linearManager = new LinearLayoutManager(this);
        seaList.setLayoutManager(linearManager);
        adapter = new SeaAdapter(stringList, context);
        seaList.setAdapter(adapter);
        adapter.setHeight(new SeaAdapter.Height() {
            @Override
            public void notic(int height) {
                height1 = height;
            }
        });
        seaList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    //cantouch==false 证明是跳转指定position
                    if (!canTouch) {
                        listsLocation.clear();
                        int lastPosition=linearManager.findLastCompletelyVisibleItemPosition();
                        for (int i = lastPosition-2; i <=lastPosition; i++) {
                            //获取坐标执行动画
                            View view = linearManager.findViewByPosition(i);
                            int[] location = new int[2];
                            view.getLocationOnScreen(location);
                            listsLocation.add(location[1]-getStatusBarHeight()+height1/2);
                            Log.d("最后一个view的坐标", "x="+location[0]+" y="+location[1]);
                        }
                        seaMan.setData(listsLocation,screenWidth);
                    }
                    canTouch = true;
                    Log.d("滑动", "停止滑动:");
                }
            }
        });
    }


    @OnClick({R.id.sea_positon, R.id.sea_go, R.id.sea_jump1, R.id.sea_jump2, R.id.sea_list, R.id.sea_man})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sea_positon:
                break;
            case R.id.sea_go:
                //滚到要动画的位置  滚动过程中禁止滑动
                canTouch = false;
                seaList.smoothScrollBy(0, Util.scrollByMyWantY(Integer.valueOf(seaPositon.getText().toString()), linearManager, height1));
                break;
            case R.id.sea_jump1:
                break;
            case R.id.sea_jump2:
                break;
            case R.id.sea_list:
                break;
            case R.id.sea_man:
                break;
        }
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    @Override
    public void doSmoething() {

    }
}
