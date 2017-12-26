package com.example.lijinduo.mydemo.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/20
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MainNeiAdapter extends RecyclerView.Adapter<MainNeiAdapter.MainNeiViewHoler> {


    @Override
    public MainNeiViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_nei, null);
        return new MainNeiViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MainNeiViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class MainNeiViewHoler extends RecyclerView.ViewHolder {

        public MainNeiViewHoler(View itemView) {
            super(itemView);
        }
    }

}
