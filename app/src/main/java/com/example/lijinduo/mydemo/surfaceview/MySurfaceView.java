package com.example.lijinduo.mydemo.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    private SurfaceHolder sfHolder;

    private Context context;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        sfHolder = getHolder();
        sfHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new MyThread()).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            Canvas canvas = sfHolder.lockCanvas(new Rect(px2dip(50),px2dip(50),px2dip(200),px2dip(200)));
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(4);
            RectF rectF=new RectF(px2dip(60),px2dip(60),px2dip(160),px2dip(160));
            Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            canvas.drawBitmap(bitmap,null,rectF,paint);
            sfHolder.unlockCanvasAndPost(canvas);
        }
    }



    /*
    * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
    */
    public  int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }
}
