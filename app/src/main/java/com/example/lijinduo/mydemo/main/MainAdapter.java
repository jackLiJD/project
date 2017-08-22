package com.example.lijinduo.mydemo.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;

import java.util.List;

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

    public MainAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public MainViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, null);
        return new MainViewHoler(view);
    }

    @Override
    public void onBindViewHolder(MainViewHoler holder, final int position) {
        holder.item_tv.setText(stringList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.itemClick(position);
            }
        });
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

    public MainViewHoler(View itemView) {
        super(itemView);
        item_tv = (TextView) itemView.findViewById(R.id.item_textview);
    }
}