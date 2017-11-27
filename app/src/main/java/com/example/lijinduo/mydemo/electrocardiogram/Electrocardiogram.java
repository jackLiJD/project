package com.example.lijinduo.mydemo.electrocardiogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/2
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class Electrocardiogram extends View {
    private String TAG="TAG";
    private int Height=0;
    private int number=0;
    private Paint paint;
    private Path path;
    private int mHeight=0;
    private int Width;
    private int oldX;
    private int oldY;

    public Electrocardiogram(Context context) {
        super(context);
    }

    public Electrocardiogram(Context context,  AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        path=new Path();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (number*200>Width) {
            canvas.translate(-200*number+Width,0);
            canvas.clipRect(200*number-Width, 0, 200*number, Height);
//            path.moveTo(number*200-Width,oldY);
        }

        path.cubicTo(number*200+100,oldY,number*200+100,mHeight,(number+1)*200,mHeight);
        oldY=mHeight;
        canvas.drawPath(path,paint);
//        path.reset();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Width= getMeasuredWidth();
        Height=getMeasuredHeight();
        if (Height != 0) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                         mHeight=new Random().nextInt(Height);
                        ++number;
                        postInvalidate();
                        Log.d(TAG, "run: "+mHeight);
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }

    }
}
