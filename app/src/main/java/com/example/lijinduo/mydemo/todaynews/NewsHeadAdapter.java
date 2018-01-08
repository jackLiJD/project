package com.example.lijinduo.mydemo.todaynews;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.MyApplication;

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
public class NewsHeadAdapter extends  RecyclerView.Adapter<NewsViewHoler>{

    private List<NewsBean> lists;
    private Context context;
    private Notic notic;
    public NewsHeadAdapter(Context context, List<NewsBean> lists) {
        this.context=context;
        this.lists=lists;
    }

    @Override
    public NewsViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_head, null);
        MyApplication.scaleScreenHelper.loadView((ViewGroup) view);
        return new NewsViewHoler(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHoler holder, final int position) {
        holder.item_news_head_tv.setText(lists.get(position).getStr());
        holder.item_news_head_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notic.notic(position);
            }
        });
        if (lists.get(position).isB()) {
            holder.item_news_head_tv.setTextColor(Color.BLUE);
        }else {
            holder.item_news_head_tv.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public interface Notic{

        void notic(int position);


    }

    public void setNotic(Notic notic){
        this.notic=notic;
    }

}
class NewsViewHoler extends RecyclerView.ViewHolder {
    public  TextView item_news_head_tv;


    public NewsViewHoler(View itemView) {
        super(itemView);
        item_news_head_tv=itemView.findViewById(R.id.item_news_head_tv);

    }



}