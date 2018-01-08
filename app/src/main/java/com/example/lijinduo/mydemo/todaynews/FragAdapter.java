package com.example.lijinduo.mydemo.todaynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/1/8
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<NewsBean> lists = new ArrayList<>();
    public FragAdapter(FragmentManager fm,List<Fragment> fragmentList,List<NewsBean> lists) {
        super(fm);
        this.fragmentList=fragmentList;
        this.lists=lists;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        Bundle args = new Bundle();
        args.putString("arg", lists.get(position).getStr());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
