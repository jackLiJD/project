package com.example.lijinduo.mydemo.album;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/9/4
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.lijinduo.mydemo.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHoler> {
    private List<String> stringList;
    private Context context;

    public AlbumAdapter(Context context,List<String> stringList) {
        this.stringList = stringList;
        this.context=context;
    }

    @Override
    public AlbumViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, null);
        return new AlbumViewHoler(view);
    }


    @Override
    public void onBindViewHolder(final AlbumViewHoler holder, final int position) {
        Glide.with(context).load(stringList.get(position)).into(new GlideDrawableImageViewTarget(holder.item_album_img) {
            @Override
            public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                super.onResourceReady(drawable, anim);
                ViewGroup.LayoutParams top = holder.item_album_img.getLayoutParams();
                holder.item_album_img.measure(0, 0);
                Log.d("g高度", "onBindViewHolder: " + holder.item_album_img.getMeasuredHeight());
//                top.height = holder.item_album_img.getMeasuredHeight();
//                holder.item_album_img.setLayoutParams(top);
            }


        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

}

class AlbumViewHoler extends RecyclerView.ViewHolder {
    public ImageView item_album_img;

    public AlbumViewHoler(View itemView) {
        super(itemView);
        item_album_img = (ImageView) itemView.findViewById(R.id.item_album_img);
    }
}