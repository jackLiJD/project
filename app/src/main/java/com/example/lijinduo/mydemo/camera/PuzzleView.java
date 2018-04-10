package com.example.lijinduo.mydemo.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/27
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PuzzleView extends android.support.v7.widget.AppCompatImageView {

    private int block = 3;

    private Bitmap bitmap;

    private Rect rect, oldRect,rect1;

    private String TAG = "拼图";
    private int edge;

    private Paint mBitPaint;
    private List<PuzzleBean> beanList = new ArrayList<>();
    private List<PuzzleBean> beanList1 = new ArrayList<>();
    private boolean draw;

    private int firstDown = -1;
    private Context context;


    public PuzzleView(Context context) {
        super(context);
        initview(context);
    }


    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    private void initview(Context context) {
        this.context = context;
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (draw) {
            for (int i = 0; i < beanList.size(); i++) {
                PuzzleBean bean = beanList.get(i);
                rect = new Rect(bean.getxStart(), bean.getyStart(), bean.getxEnd(), bean.getyEnd());
                int oldI = bean.getOldPosition();
                Log.d("多多", oldI + "" + bean.isMoveState());
                oldRect = new Rect(beanList.get(oldI).getxStart(), beanList.get(oldI).getyStart(), beanList.get(oldI).getxEnd(), beanList.get(oldI).getyEnd());
                if (beanList.get(oldI).isMoveState()) {
                    mBitPaint.setColor(Color.WHITE);
                    mBitPaint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(rect, mBitPaint);
                } else {
                    canvas.drawBitmap(bitmap, oldRect, rect, mBitPaint);
                }
//                if (firstDown !=-1) {
//                    PuzzleBean bean1 = beanList.get(firstDown-1);
//                    rect1 = new Rect(bean1.getxStart(), bean1.getyStart(), bean1.getxEnd(), bean1.getyEnd());
//                    mBitPaint.setColor(Color.RED);
//                    mBitPaint.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(rect1, mBitPaint);
//
//                }
            }
        }

        super.onDraw(canvas);
    }

    public void setImageBitMap(Bitmap bitMap) {
        //此处用imageBitmap 画不出来东西
        Drawable drawable = new BitmapDrawable(bitMap);
        setBackground(drawable);
//        setImageBitmap(bitMap);
        bitmap = convertViewToBitmap();
        int width = bitmap.getWidth() / block;
        int height = bitmap.getHeight() / block;
        edge = bitmap.getHeight() / block;
        int shang = 0;
        int yu = 0;
        int suiji = new Random().nextInt(block*block);
        Log.d(TAG, "多多随机数: " + suiji);


        for (int i = 0; i < block * block; i++) {
            shang = i / block;
            yu = i % block;
            PuzzleBean bean = new PuzzleBean();
            bean.setxStart(yu * width);
            bean.setyStart(shang * height);
            bean.setxEnd((yu + 1) * width);
            bean.setyEnd((shang + 1) * height);
            bean.setPosition(i);
            bean.setOldPosition(i);
            if (i == suiji) {
                bean.setMoveState(true);
            } else {
                bean.setMoveState(false);
            }

            beanList.add(bean);
            beanList1.addAll(beanList);
        }
        // 混乱
        Collections.shuffle(beanList);
        draw = true;
        invalidate();
    }

    public Bitmap convertViewToBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int currtDown = positionTouch((int) event.getX(), (int) event.getY());
                //是第一次按下
                if (firstDown == -1) {
                    firstDown = currtDown;
                } else {
                    //是相邻的两个view
                    if (Math.abs(currtDown - firstDown) == block || ((currtDown - firstDown) == 1 && currtDown % block != 1) || ((firstDown - currtDown == 1) && firstDown % block != 1)) {
                        if (beanList.get(firstDown - 1).isMoveState() || beanList.get(currtDown - 1).isMoveState()) {
                            Collections.swap(beanList, firstDown - 1, currtDown - 1);
                            invalidate();
                            if (beanList.equals(beanList1)) {
                                Toast.makeText(context, "拼图成功", 200).show();
                            }
                        }
//                        firstDown=  currtDown;
                        firstDown = -1;
                    } else {
//                        firstDown=  currtDown;
                        firstDown = -1;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:


                break;
        }
        return super.onTouchEvent(event);
    }


    private int positionTouch(int x, int y) {
        int shangX = x / edge;
        int shangY = y / edge;
        return shangY * block + shangX + 1;
    }


}
