package com.example.lijinduo.mydemo.backlinear;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.AppTool;
import com.example.lijinduo.mydemo.view.BackLinearlayout;
import com.example.lijinduo.mydemo.view.PictureHandlerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/12
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BackLinearAct extends PictureHandlerActivity {
    @BindView(R.id.start_other)
    BackLinearlayout startOther;
    //    @BindView(R.id.start_xj)
//    ImageView startXj;
    private Context context = BackLinearAct.this;
    private VideoView videoview;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_backlinear);
        ButterKnife.bind(this);
        videoview= (VideoView) findViewById(R.id.videoview);
        startOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp("com.zw.zwlc");
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                ComponentName cn = new ComponentName("com.zw.zwlc", "com.zw.zwlc.activity.WelcomeAct");
//                intent.setComponent(cn);
//                startActivity(intent);
            }
        });
//        startXj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(BackLinearAct.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
//                    ActivityCompat.requestPermissions(BackLinearAct.this,
//                            new String[]{Manifest.permission.CAMERA},
//                            1234);
//                } else {
//                    //有权限，直接拍照
//                    startCamera(null);
//                }
//
//            }
//        });
        Log.d("啊实打实的", "onCreate: ");
//        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(800, 800);
//        ImageView iv = new ImageView(BackLinearAct.this);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        iv.setBackgroundResource(R.drawable.andes);
//        mParams.gravity = Gravity.BOTTOM;
//        addContentView(iv, mParams);
////            Glide.with(BackLinearAct.this).load("file:///android_asset/ic_guide_03.gif").diskCacheStrategy(DiskCacheStrategy.NONE).into(imageview);
//        Glide.with(BackLinearAct.this).load(getAssetsCacheFile("ic_guide_03.gif")).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    @Override
    protected String getCameraAbsolutePath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    @Override
    protected Crop getCrop() {
        return new Crop().setCrop(false).setAspectX(590).setAspectY(374).setOutputX(590).setOutputY(374);
    }

    @Override
    protected void resultPhotoPath(ImageView view, String photoPath) {
//        return Environment.getExternalStorageDirectory().getPath();
        AppTool.log("返回的路径", "返回的路径=====" + photoPath);
//        startXj.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        //本地加载图片
//        startXj.setImageBitmap(revitionImageSize(photoPath));

    }


    public String getAssetsCacheFile(String fileName) {
        File cacheFile = new File(getCacheDir(), fileName);
        try {
            InputStream inputStream = getAssets().open(fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("地址", "getAssetsCacheFile: " + cacheFile.getAbsolutePath());
        return cacheFile.getAbsolutePath();
    }

    // 压缩图片
    public static Bitmap revitionImageSize(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;// 压缩好比例大小后再进行质量压缩
    }
    private void openApp(String packageName) {
        PackageInfo pi = null;
        try {
            pi = getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = getPackageManager().queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null ) {
//            String packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }
    }
}
