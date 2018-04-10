package com.example.lijinduo.mydemo.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class CameraAct extends BaseActivity {
    @BindView(R.id.camera_img)
    PuzzleView cameraImg;
    @BindView(R.id.open_camera)
    Button openCamera;
    @BindView(R.id.choose_album)
    Button chooseAlbum;
    private Context context = CameraAct.this;
    private File tempFile;
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";
    //判断页面是否加载完成
    public boolean isComplete = false;
    private static final int REQUEST_CODE_PICK = 0;        // 从相册中选择
    private static final int REQUEST_CODE_TAKE = 1;        // 拍照
    private static final int REQUEST_CODE_CUTTING = 2;        // 结果
    private Bitmap bitmap;
    private List list=new ArrayList();
    private List list1=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_camera);
        ButterKnife.bind(this);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list1.addAll(list);

        Log.d("是否相等", "onCreate: "+(list.equals(list1)));
        Collections.reverse(list);
        Log.d("是否相等", "onCreate: "+(list.equals(list1)));


    }

    private void creat() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        isComplete = true;
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, REQUEST_CODE_TAKE);

    }


    /**
     * @param requestCode  请求code
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    creat();
                } else {
                    Toast.makeText(context, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    isComplete = true;
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                    startActivityForResult(intent1, REQUEST_CODE_PICK);
                } else {
                    Toast.makeText(context, "权限未确认", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @OnClick({R.id.open_camera, R.id.choose_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open_camera:

                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 2);
                        return;
                    } else {
                        //上面已经写好的拨号方法
                        creat();
                    }
                } else {
                    //上面已经写好的拨号方法
                    creat();
                }
                break;
            case R.id.choose_album:

                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 3);
                        return;
                    } else {
                        // 激活系统图库，选择一张图片
                        Intent intent1 = new Intent(Intent.ACTION_PICK);
                        intent1.setType("image/*");
                        isComplete = true;
                        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                        startActivityForResult(intent1, REQUEST_CODE_PICK);
                    }
                } else {
                    // 激活系统图库，选择一张图片
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");
                    isComplete = true;
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                    startActivityForResult(intent1, REQUEST_CODE_PICK);
                }


                break;
        }
    }

    /**
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == REQUEST_CODE_TAKE) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(context, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_CUTTING) {
            // 从剪切图片返回的数据
            if (data != null) {
                bitmap = data.getParcelableExtra("data");
                //保存到SharedPreferences
                if (bitmap != null) {
                    cameraImg.setImageBitMap(bitmap);

                }
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        isComplete = true;
        startActivityForResult(intent, REQUEST_CODE_CUTTING);
    }
}
