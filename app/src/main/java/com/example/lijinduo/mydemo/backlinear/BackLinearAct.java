package com.example.lijinduo.mydemo.backlinear;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    @BindView(R.id.start_xj)
    ImageView startXj;
    @BindView(R.id.shadowlin)
    LinearLayout shadowlin;
    @BindView(R.id.html_tv)
    TextView htmlTv;
    private Context context = BackLinearAct.this;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_backlinear);
        ButterKnife.bind(this);
        startOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                                String url="mqqwpa://im/chat?chat_type=wpa&uin=1102377755";
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                openApp("com.zw.zwlc");
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                ComponentName cn = new ComponentName("com.zw.zwlc", "com.zw.zwlc.activity.WelcomeAct");
//                intent.setComponent(cn);
//                startActivity(intent);
            }
        });
        startXj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(BackLinearAct.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(BackLinearAct.this,
                            new String[]{Manifest.permission.CAMERA},
                            1234);
                } else {
                    //有权限，直接拍照
                    startCamera(null);
                }

            }
        });
        Log.d("啊实打实的", "onCreate: ");

        ShadowProperty sp = new ShadowProperty()
//                .setShadowColor(0x77000000)
                .setShadowColor(Color.LTGRAY)
//                .setShadowDy(dip2px(this, 5f))
//                .setShadowDx(dip2px(this, 5f))
                .setShadowRadius(dip2px(this, 3))
//                .setShadowSide(10)
                .setShadowSide(ShadowProperty.ALL);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.WHITE, 0, 0);
        ViewCompat.setBackground(shadowlin, sd);
        ViewCompat.setLayerType(shadowlin, ViewCompat.LAYER_TYPE_SOFTWARE, null);
//        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(800, 800);
//        ImageView iv = new ImageView(BackLinearAct.this);
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);
//        iv.setBackgroundResource(R.drawable.andes);
//        mParams.gravity = Gravity.BOTTOM;
//        addContentView(iv, mParams);
////            Glide.with(BackLinearAct.this).load("file:///android_asset/ic_guide_03.gif").diskCacheStrategy(DiskCacheStrategy.NONE).into(imageview);
//        Glide.with(BackLinearAct.this).load(getAssetsCacheFile("ic_guide_03.gif")).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        String source = "阿斯顿拉萨卡的萨克斯大家；大世界了解你；  解散阿基诺说法"
                + "<font color=#f56738><strong>" + "这是改变颜<br />色加粗文字" + "</strong></font>"+"实打实大\r师的大\n神";
        String strcontent = ToDBC(source);
        htmlTv.setText(Html.fromHtml(strcontent));


    }
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
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
        startXj.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        //本地加载图片
        startXj.setImageBitmap(revitionImageSize(photoPath));

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
        if (ri != null) {
//            String packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (Throwable throwable) {
            // igonre
        }
        return 0;
    }
}
