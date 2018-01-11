package com.example.lijinduo.mydemo.todaynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
 * 创建日期：2018/1/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ViewPageNestAct extends BaseActivity {
    ScalePageTransformer scalePageTransformer;
    @BindView(R.id.viewpage1)
    ClipViewPager viewpage1;
    @BindView(R.id.button110)
    Button button110;
    @BindView(R.id.button11)
    Button button11;
    private List<String> stringList;
    private List<NewsBean> lists = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private int page;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewpage);
        ButterKnife.bind(this);
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
            fragmentList.add(new Fragment2());
        }
        scalePageTransformer = new ScalePageTransformer(3);
        viewpage1.setPageTransformer(true, scalePageTransformer);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragmentList, lists);
        viewpage1.setOffscreenPageLimit(fragmentList.size());
        viewpage1.setAdapter(adapter);



    }


    @OnClick({R.id.button110, R.id.button11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button110:
                viewpage1.setCurrentItem(0);
                break;
            case R.id.button11:
                viewpage1.setCurrentItem(++page);
                break;
        }
    }
}
