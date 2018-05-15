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
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
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
    private int width;

    public AlbumAdapter(Context context, List<String> stringList, int width) {
        this.stringList = stringList;
        this.context=context;
        this.width=width;
    }

    @Override
    public AlbumViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, null);
        return new AlbumViewHoler(view);
    }


    @Override
    public void onBindViewHolder(final AlbumViewHoler holder, final int position) {
        ViewGroup.LayoutParams params = holder.item_album_img.getLayoutParams();
        if (stringList.get(position).contains("155843")) {
            return;
        }


        params.height=width/3*getImageHeight(stringList.get(position))/getImageWidth(stringList.get(position));
        holder.item_album_img.setLayoutParams(params);
        Glide.with(context).load(stringList.get(position)).into(holder.item_album_img);
        holder.item_album_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("图片路径", "onClick: "+stringList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    private  int getImageHeight(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        return options.outHeight;
    }

    private  int getImageWidth(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**／
         *options.outHeight为原始图片的高
         */
        return options.outWidth;
    }

}



class AlbumViewHoler extends RecyclerView.ViewHolder {
    public ImageView item_album_img;

    public AlbumViewHoler(View itemView) {
        super(itemView);
        item_album_img = (ImageView) itemView.findViewById(R.id.item_album_img);
    }
}

