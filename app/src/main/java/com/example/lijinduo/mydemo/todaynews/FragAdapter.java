package com.example.lijinduo.mydemo.todaynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


import java.io.Serializable;
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
public class FragAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List lists = new ArrayList<>();
    private FragmentManager fm;

    public FragAdapter(FragmentManager fm, List<Fragment> fragmentList, List lists) {
        super(fm);
        this.fragmentList = fragmentList;
        this.lists = lists;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        Bundle args = new Bundle();
        args.putSerializable("bean", (Serializable) lists.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


//    @Override
//    public int getItemPosition(Object object) {
//
//        return POSITION_NONE;
//    }
//    private int mChildCount = 0;
//
//    @Override
//    public void notifyDataSetChanged() {
//        mChildCount = getCount();
//        super.notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        if (mChildCount > 0) {
//            mChildCount--;
//            return POSITION_NONE;
//        }
//        return super.getItemPosition(object);
//    }

}
