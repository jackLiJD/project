package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lijinduo.mydemo.animation.AnimatorPath;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class AnimationVIew extends View{
    Path path;
    Paint paint;
    AnimatorPath animatorPath;
//    http://blog.csdn.net/szqsdq/article/details/53838111

    public AnimationVIew(Context context) {
        this(context,null);
    }

    public AnimationVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        path = new Path();
        path.moveTo(100, 100);
        path.quadTo(200, 300, 300, 100);
        path.quadTo(400,400,500,0);
//        path.lineTo(600,100);
//        path.lineTo(600,600);
//        path.cubicTo(200,350,300,50,400,100);
        path.close();
        canvas.drawPath(path,paint);
    }

    private void startAnimator(){
        animatorPath=new AnimatorPath();


//        ObjectAnimator objAnimator = ObjectAnimator.ofObject(this, "PointPath", new MyEvalueTor(), animatorPath.getPoints().toArray());
    }

}
