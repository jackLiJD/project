package com.example.lijinduo.mydemo.todaynews;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.IndicatorView;
import com.example.lijinduo.mydemo.view.NoTouchViewpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/8
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Fragment2 extends Fragment {


    @BindView(R.id.viewpage2)
    ClipViewPager viewpage2;
    @BindView(R.id.button10)
    Button button10;
    Unbinder unbinder;
    FragAdapter2 adapter;
    private List<String> stringList;
    private List<Fragment> fragmentList = new ArrayList<>();
    ScalePageTransformer scalePageTransformer;
    @BindView(R.id.guide_myline)
    IndicatorView guideMyline;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.act_frag2, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        String title = mBundle.getString("arg");
        button10.setText(title);
        String[] stringArray = getResources().getStringArray(R.array.main_item_title);
        stringList = new ArrayList<>(Arrays.asList(stringArray));
        for (int i = 0; i < stringList.size(); i++) {
            if (i <6) {
                NewsBean newsBean = new NewsBean();
                newsBean.setStr(stringList.get(i).toString());
                if (i == 0) {
                    newsBean.setB(true);
                } else {
                    newsBean.setB(false);
                }
                fragmentList.add(new Fragment3(title));
            }

        }
        Log.d("这他妈长度", "onCreateView: "+fragmentList.size());
        scalePageTransformer = new ScalePageTransformer(3);
        viewpage2.setPageTransformer(true, scalePageTransformer);
        adapter = new FragAdapter2(getChildFragmentManager(), fragmentList);
        viewpage2.setAdapter(adapter);
        guideMyline.attribute(6, 8, 5);
        viewpage2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                guideMyline.moveView(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                viewpage2.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


}
