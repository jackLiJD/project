package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/6
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MagnifyingGlassView extends android.support.v7.widget.AppCompatImageView {
    private Rect mSrcRect, mDestRect,mDestRect1;
    private Paint mBitPaint;
    private int x, y;
    private boolean isTouch;
    Bitmap bm;
    PorterDuff.Mode mode=PorterDuff.Mode.SRC_IN;

    public MagnifyingGlassView(Context context) {
        super(context);
        initPaint();
    }

    public MagnifyingGlassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    private void initPaint() {
        setBackgroundResource(R.drawable.timi);
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mSrcRect = new Rect(x - 100, y - 100, x + 100, y + 100);
        mDestRect = new Rect(x - 200, y - 200, x + 200, y + 200);
        mDestRect1 = new Rect(x - 300, y - 300, x + 100, y + 100);
        if (isTouch == true) {
            //清屏功能  投机取巧
            mDestRect = new Rect(x - 100, y - 100, x + 100, y + 100);
            canvas.drawBitmap(bm, mSrcRect, mDestRect, mBitPaint);
            isTouch = false;
        } else {
            if (bm != null) {
                int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
                canvas.drawOval(new RectF(mDestRect), mBitPaint);
                mBitPaint.setXfermode(new PorterDuffXfermode(mode));
                canvas.drawBitmap(bm, mSrcRect,mDestRect, mBitPaint);
                mBitPaint.setXfermode(null);
                canvas.restoreToCount(layerId);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (bm == null) {
                    //合理的位置
                    bm = convertViewToBitmap();
                }
                x = (int) event.getX();
                y = (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                break;

            case MotionEvent.ACTION_UP:
                isTouch = true;
                break;

        }
        invalidate();

        return true;
    }



    public  Bitmap convertViewToBitmap() {
        Bitmap bitmap= Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }


    public void setType(PorterDuff.Mode mode){
        this.mode=mode;
    }

}
