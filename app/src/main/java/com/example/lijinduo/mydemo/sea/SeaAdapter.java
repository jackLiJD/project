package com.example.lijinduo.mydemo.sea;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.MyApplication;

import java.util.List;
import java.util.Random;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/17
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class SeaAdapter extends RecyclerView.Adapter<SeaViewHoler> {
    private List<String> stringList;
    private Context context;

    public SeaAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public SeaViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sea, null);
        MyApplication.scaleScreenHelper.loadView((ViewGroup) view);
        return new SeaViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final SeaViewHoler holder, final int position) {
        holder.item_tv.setText(stringList.get(position));
        if (position == 0) {
            holder.item_sea_btn.setVisibility(View.VISIBLE);
        }else{
            holder.item_sea_btn.setVisibility(View.GONE);
        }
        if (position == 1) {
            int heightView = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            holder.item_sea_lin.measure(0, heightView);
            height.notic(holder.item_sea_lin.getMeasuredHeight());
        }
        holder.item_sea_lin.setBackgroundColor(Color.parseColor("#"+getRandColorCode()));
        if (position%2==1) {
            holder.item_sea_lin.setGravity(Gravity.RIGHT);
        }else{
            holder.item_sea_lin.setGravity(Gravity.LEFT);
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
    public void setHeight(Height height){
        this.height=height;

    }
    interface  Height{
        void notic(int height);
    }
    private Height height;
    public String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }



}







class SeaViewHoler extends RecyclerView.ViewHolder {
    public TextView item_tv;
    public LinearLayout item_sea_lin;
    public Button item_sea_btn;

    public SeaViewHoler(View itemView) {
        super(itemView);
        item_tv = itemView.findViewById(R.id.item_sea_tv);
        item_sea_lin=itemView.findViewById(R.id.item_sea_lin);
        item_sea_btn=itemView.findViewById(R.id.item_sea_btn);
    }
}