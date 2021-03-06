package com.example.lijinduo.mydemo.animation;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/21
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhengliang on 2016/10/15 0015.
 * 客户端使用类,记录一系列的不同移动轨迹
 */

public class AnimatorPath extends Path {
    //一系列的轨迹记录动作
    private List<PathPoint> mPoints = new ArrayList<>();

    /**
     * 移动位置到:
     * @param x
     * @param y
     */
    public void moveTo(float x,float y){
        mPoints.add(PathPoint.moveTo(x,y));
    }

    /**
     * 直线移动
     * @param x
     * @param y
     */
    public void lineTo(float x,float y){
        mPoints.add(PathPoint.lineTo(x,y));
    }

    /**
     * 二阶贝塞尔曲线移动
     * @param c0X
     * @param c0Y
     * @param x
     * @param y
     */
    public void secondBesselCurveTo(float c0X, float c0Y,float x,float y){

        mPoints.add(PathPoint.secondBesselCurveTo(c0X,c0Y,x,y));
    }

    /**
     * 三阶贝塞尔曲线移动
     * @param c0X
     * @param c0Y
     * @param c1X
     * @param c1Y
     * @param x
     * @param y
     */
    public void thirdBesselCurveTo(float c0X, float c0Y, float c1X, float c1Y, float x, float y){
        mPoints.add(PathPoint.thirdBesselCurveTo(c0X,c0Y,c1X,c1Y,x,y));
    }
    /**
     *
     * @return  返回移动动作集合
     */
    public Collection<PathPoint> getPoints(){
        return mPoints;
    }
}