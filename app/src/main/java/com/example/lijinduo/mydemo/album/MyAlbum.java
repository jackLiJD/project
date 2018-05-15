package com.example.lijinduo.mydemo.album;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0\
 * 创建日期：2017/9/4
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MyAlbum extends BaseActivity {
    private final int REQUEST_CODE_ASK_CALL_PHONE=1234;
    private List<String> imagePath=new ArrayList<String>();
    private Context mContext=MyAlbum.this;
    String caocao="黑切，韧鞋，冰痕，反甲，不死鸟，不详";
    String lanlingwang="打野-复活，鞋，黑切，冰痕，魔女，破军";
    String yangjian="黑切，鞋，冰痕，反甲，魔女，复活";
    String hanxin="打野-复活，鞋，黑切，吸血，宗师，暴击刀";
    CacheWebView cacheWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_album);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        onCall();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.album_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        AlbumAdapter adapter = new AlbumAdapter(mContext,imagePath,width);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void doSmoething() {

    }

    private void queryPhotos() {
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取图片的生成日期
            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            imagePath.add(new String(data, 0, data.length - 1));
        }
    }

    private void onCall(){
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) mContext,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }else{
                queryPhotos();
            }
        } else {
            queryPhotos();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    queryPhotos();
                } else {
                    Toast.makeText(mContext, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
