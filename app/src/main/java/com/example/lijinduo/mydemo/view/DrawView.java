package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lijinduo.mydemo.tool.AppTool;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/9/1
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class DrawView extends View {
    Paint paint;
    Path path;

    public DrawView(Context context) {
        super(context);

    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                AppTool.log("ACTION_DOWN");
                path.moveTo(event.getX(), event.getY());

                break;

            case MotionEvent.ACTION_MOVE:
                AppTool.log("ACTION_MOVE");
                path.lineTo(event.getX(), event.getY());
                        invalidate();
                break;

            case MotionEvent.ACTION_UP:
                AppTool.log("ACTION_UP");
                break;


        }
        return super.onTouchEvent(event);
    }


}
