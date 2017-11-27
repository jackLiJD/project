package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.mymap.MyMapAct;
import com.example.lijinduo.mydemo.mymap.MyMapBean;
import com.example.lijinduo.mydemo.tool.AppTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class PathView extends View {
    //获取屏幕的宽度 x
    private int screenX=0;
    //获取屏幕的高度 y
    private int screenY=0;
    private Paint paint;
    Path path ;
    private String TAG="MyMap";
    //15个随机生成的坐标点
    private List<MyMapBean> randomXYs=new ArrayList<>();
    //收集滑动区域的所有坐标
    private List<MyMapBean> randomAlls=new ArrayList<>();
    //旧的坐标x
    private int oldX;
    //旧的坐标y
    private int oldY;

    private int num=0;

    public PathView(Context context) {
        super(context);
    }
    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        Shader mShader=new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.andes),Shader.TileMode.REPEAT,Shader.TileMode.MIRROR);
        path = new Path();
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //设置画笔未实心
        paint.setStyle(Paint.Style.STROKE);
        //设置颜色／
        paint.setColor(Color.RED);
        //设置画笔宽度
        paint.setStrokeWidth(3);
//        paint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha(255);
            for (int i = 0; i < randomXYs.size(); i++) {
                Log.d(TAG, randomXYs.get(i).getX()+"onDraw: ");
                canvas.drawCircle(randomXYs.get(i).getX(),randomXYs.get(i).getY(),5,paint);
            }
        paint.setAlpha(80);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MyMapBean bean=new MyMapBean();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                AppTool.log("ACTION_DOWN");
                path.moveTo(event.getX(),event.getY());
                bean.setX((int) event.getX());
                bean.setY((int) event.getY());
                randomAlls.add(bean);
                oldX= (int) event.getX();
                oldY= (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                AppTool.log("ACTION_MOVE");
                path.lineTo(event.getX(),event.getY());

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                path.close();
                invalidate();

                for (int i = 0; i <randomXYs.size() ; i++) {
                    if (pointInPath(path,randomXYs.get(i).getX(),randomXYs.get(i).getY())) {
                        ++num;
                    }
                }
                Toast.makeText(getContext(),"选中了"+num+"个商家",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onDraw个数: "+num);
                num=0;
                break;
        }
        return true;
    }


    /**
     * 随机生成的坐标
     */
    private void coordinatesRandom(){
        for (int i = 0; i < 15; i++) {
            MyMapBean bean=new MyMapBean();
            bean.setX(new Random().nextInt(screenX+1));
            bean.setY(new Random().nextInt(screenY+1));
            randomXYs.add(bean);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenX=getMeasuredHeight();
        screenY=getMeasuredWidth();
        coordinatesRandom();
    }

    private boolean pointInPath(Path path, int x, int y) {
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(path, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));
        return region.contains(x, y);
    }
}

