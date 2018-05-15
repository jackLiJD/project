package com.example.lijinduo.mydemo.main;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MainAdapter extends RecyclerView.Adapter<MainViewHoler> {
    private List<String> stringList;
    private onItemClick onItemClick;
    private Context context;

    public MainAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public MainViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, null);
        return new MainViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final MainViewHoler holder, final int position) {


        holder.recycle_main_nei.setLayoutManager(new GridLayoutManager(context, 1));
        holder.recycle_main_nei.setAdapter(holder.adapter);
        holder.item_tv.setText(stringList.get(position));





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemClick(position);
            }
        });
        holder.itemView.setContentDescription(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    public interface onItemClick {

        void itemClick(int position);

    }

    public void setItemListener(onItemClick onItemClick) {

        this.onItemClick = onItemClick;

    }


}

class MainViewHoler extends RecyclerView.ViewHolder {
    public TextView item_tv;
    public RecyclerView recycle_main_nei;
    public MainNeiAdapter adapter = new MainNeiAdapter();

    public MainViewHoler(View itemView) {
        super(itemView);
        item_tv = itemView.findViewById(R.id.item_textview);
        recycle_main_nei = itemView.findViewById(R.id.recycle_main_nei);
    }
}