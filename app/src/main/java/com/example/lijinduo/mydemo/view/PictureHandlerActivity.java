package com.example.lijinduo.mydemo.view;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.lijinduo.mydemo.BaseActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * <strong>自定义图像处理</strong><br/>
 * <li>启动相册{@link #startAlbum()}</li> <li>启动系统照相机 {@link #startCamera()}</li>
 * <li>拍照保存的绝对路径 {@link #getCameraAbsolutePath()}</li> <li>图片剪切配置
 * {@link #getCrop()}</li> <li>当使用者拍照结束或选择相册图片后会通过此方法返回图片路径
 * {@link #resultPhotoPath(String photoPath)}</li>
 *
 * @author 朱朝旭
 */
@SuppressLint({"SimpleDateFormat", "SdCardPath"})
public abstract class PictureHandlerActivity extends BaseActivity {
    /**
     * 相册返回标识
     */
    public static final int FOR_ALBUM = 4001;

    /**
     * 照相机返回标识
     */
    public static final int FOR_CAMERA = 4002;

    /**
     * 切图返回标识
     */
    public static final int FOR_CROP = 4003;

    /**
     * 数据库名
     */
    private static final String DB_NAME = "PictureHandlerActivity";

    /**
     * KEY名+
     */
    private static final String KEY_PICTURE = "Picture";

    /**
     * 时间格式
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    /**
     * 首选项(保存拍照图片绝对路径)
     */
    private SharedPreferences sharedPreferences;

    /**
     * 拍照绝对路径，裁剪绝对路径
     */
    private Uri cropUri;

    /**
     * 当前视图
     */
    private ImageView currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(DB_NAME, 0);
    }

    /**
     * 启动相册
     * <p/>
     * 绝对路径
     */
    public void startAlbum(ImageView view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), FOR_ALBUM);
        currentView = view;
    }

    /**
     * 启动系统照相机
     */
    public void startCamera(ImageView view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, createCameraAbsoluteUri());
        startActivityForResult(intent, FOR_CAMERA);
        currentView = view;
    }

//    //获取真实路径
//    public String getRealPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            ;
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//        return res;
//    }

    /**
     * 通过uri获取文件的绝对路径
     *
     * @param uri
     * @return
     */
    @SuppressWarnings("deprecation")
    private String getAbsoluteImagePath(Uri uri) {
        String imagePath = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.getCount() > 0 && cursor.moveToFirst())
                imagePath = cursor.getString(column_index);
        }
        return imagePath;
    }

    /**
     * Intent返回值处理
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case FOR_ALBUM:
                if (getCrop().isCrop()) {
                    startCrop(getCrop().setOriginalUri(data.getData()));

                } else {

                    resultPhotoPath(currentView,
                            getPath(PictureHandlerActivity.this, data.getData()));
                }
                break;
            case FOR_CAMERA:

                if (getCrop().isCrop())
                    startCrop(getCrop().setOriginalUri(readPicture()));
                else
                    resultPhotoPath(currentView, readPicture().getPath());
                break;
            case FOR_CROP:
                resultPhotoPath(currentView, cropUri.getPath());
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        //获取版本号是否
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/"
                            + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = MediaStore.MediaColumns._ID + "=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection,
                        selectionArgs);
            }
        }
        // MediaStore (and general)
        //忽略大小写IgnoreCase
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File  getScheme 发音 [skiːm]
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    public static String getDataColumn(Context context, Uri uri,
                                       String selection, String[] selectionArgs) {
        Cursor cursor = null;
        //媒体商店。媒体列。数据 MediaStore.MediaColumns.DATA
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        //外部存储文件  getAuthority 发音 [ɔː'θɒrɪtɪ]
        return "com.android.externalstorage.documents".equals(uri
                .getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        //android提供的下载文档
        return "com.android.providers.downloads.documents".equals(uri
                .getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        //媒体文档
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }


    public static boolean isGooglePhotosUri(Uri uri) {
        //应用提供的文档
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }


    /**
     * 启动图片裁剪界面
     *
     * @param crop
     */
    private void startCrop(Crop crop) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(crop.getOriginalUri(), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", createCropUriBy(crop.getOriginalUri()));
        intent.putExtra("aspectX", crop.getAspectX());
        intent.putExtra("aspectY", crop.getAspectY());
        intent.putExtra("outputX", crop.getOutputX());
        intent.putExtra("outputY", crop.getOutputY());
        intent.putExtra("scale", crop.isScale());
        intent.putExtra("scaleUpIfNeeded", crop.isScaleUpIfNeeded());
        startActivityForResult(intent, FOR_CROP);
    }


    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    private boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n')
                return false;
        }
        return true;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    private String getFileFormat(String fileName) {
        if (isEmpty(fileName))
            return "";
        int point = fileName.lastIndexOf('.');
        return fileName.substring(point + 1);
    }

    /**
     * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路径
     *
     * @param uri
     * @return
     */
    private String getAbsolutePathFromNoStandardUri(Uri uri) {
        String path = null;
        String uriPath = Uri.decode(uri.toString());
        String pre_sdcard = "file:///sdcard/";
        String pre_sdcard_mnt = "file:///mnt/sdcard/";
        if (uriPath.startsWith(pre_sdcard))
            path = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + uriPath.substring(pre_sdcard.length());
        else if (uriPath.startsWith(pre_sdcard_mnt))
            path = Environment.getExternalStorageDirectory().getPath()
                    + File.separator
                    + uriPath.substring(pre_sdcard_mnt.length());
        return path;
    }

    public static String getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 480f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 2;// be=1表示不缩放
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
        Bitmap bmp = bitmap;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        try {
            FileOutputStream out = new FileOutputStream(srcPath);
            bmp.compress(Bitmap.CompressFormat.JPEG, options, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block，。
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return srcPath;
    }

    /**
     * 裁剪后图片的绝对路径
     *
     * @param uri
     * @return
     */
    private Uri createCropUriBy(Uri uri) {
        createAbsoluteFile();
        String thePath = getAbsolutePathFromNoStandardUri(uri);
        if (isEmpty(thePath))
            thePath = getAbsoluteImagePath(uri);
        String ext = getFileFormat(thePath);
        File file = new File(getCameraAbsolutePath() + "/xx"
                + "_crop" + "." + (isEmpty(ext) ? "jpg" : ext));
        cropUri = Uri.fromFile(file);
        return cropUri;
    }


    private Uri createCropUriBy1(Uri uri) {
        createAbsoluteFile();
        String thePath = getAbsolutePathFromNoStandardUri(uri);
        if (isEmpty(thePath))
            thePath = getAbsoluteImagePath(uri);
        String ext = getFileFormat(thePath);
        File file = new File(getCameraAbsolutePath() + "/" + getTime()
                + "_crop" + "." + (isEmpty(ext) ? "jpg" : ext));
        Uri cropUri1 = Uri.fromFile(file);
        return cropUri1;
    }


    /**
     * 创建拍照文件路径
     *
     * @return
     */
    private Uri createCameraAbsoluteUri() {
        createAbsoluteFile();
        Uri uri = Uri.fromFile(new File(getCameraAbsolutePath() + "/camera_"
                + "headpath" + "xx" + ".jpg"));
        savePicture(uri);
        return uri;
    }

    /**
     * 创建路径
     */
    private void createAbsoluteFile() {

        File file = new File(getCameraAbsolutePath());
        if (!file.exists())
            file.mkdirs();
    }

    /**
     * 存储Uri
     *
     * @param uri
     */
    private void savePicture(Uri uri) {
        sharedPreferences.edit().putString(KEY_PICTURE, uri.toString())
                .commit();
    }

    /**
     * 读取Uri
     *
     * @return
     */
    private Uri readPicture() {
        return Uri.parse(sharedPreferences.getString(KEY_PICTURE, ""));
    }

    /**
     * 获取时间
     *
     * @return
     */
    @SuppressWarnings("unused")
    private String getTime() {
        return simpleDateFormat.format(new Date());
    }

    /**
     * 图片剪切配置（重写该方法,Crop对象setCrop参数true时启动剪切功能）
     *
     * @return
     */
    protected Crop getCrop() {
        return new Crop();
    }

    /**
     * 拍照保存的绝对路径
     *
     * @return
     */
    protected abstract String getCameraAbsolutePath();

    /**
     * 当使用者拍照结束或选择相册图片后会通过此方法返回图片路径
     *
     * @param photoPath 图片路径
     */
    protected abstract void resultPhotoPath(ImageView view, String photoPath);

    /**
     * 图片剪切配置
     *
     * @author youdro 朱朝旭
     */
    public class Crop {
        private Uri originalUri;
        private int aspectX = 1, aspectY = 1, outputX = 1, outputY = 1;
        private boolean scale = true, scaleUpIfNeeded = true, crop = false;

        public Crop setCrop(boolean crop) {
            this.crop = crop;
            return this;
        }

        public boolean isCrop() {
            return crop;
        }

        public Crop setOriginalUri(Uri originalUri) {
            this.originalUri = originalUri;
            return this;
        }

        public Uri getOriginalUri() {
            return originalUri;
        }

        public Crop setAspectX(int aspectX) {
            this.aspectX = aspectX;
            return this;
        }

        public int getAspectX() {
            return aspectX;
        }

        public Crop setAspectY(int aspectY) {
            this.aspectY = aspectY;
            return this;
        }

        public int getAspectY() {
            return aspectY;
        }

        public Crop setOutputX(int outputX) {
            this.outputX = outputX;
            return this;
        }

        public int getOutputX() {
            return outputX;
        }

        public Crop setOutputY(int outputY) {
            this.outputY = outputY;
            return this;
        }

        public int getOutputY() {
            return outputY;
        }

        public Crop setScale(boolean scale) {
            this.scale = scale;
            return this;
        }

        public boolean isScale() {
            return scale;
        }

        public Crop setScaleUpIfNeeded(boolean scaleUpIfNeeded) {
            this.scaleUpIfNeeded = scaleUpIfNeeded;
            return this;
        }

        public boolean isScaleUpIfNeeded() {
            return scaleUpIfNeeded;
        }
    }
}
