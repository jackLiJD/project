package com.example.lijinduo.mydemo.fuzzy;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BlurBitmap {
    /**
     * 图片缩放比例
     */
    private final float BITMAP_SCALE = 0.4f;

    /**
     * 最大模糊度(在0.0到25.0之间)
     */
    private float BLUR_RADIUS = 25f;
    Bitmap inputBitmap;
    Bitmap outputBitmap;
    private float percent=1f;
    public Bitmap blur(Context context,Bitmap bitmap){
        int width=Math.round(bitmap.getWidth()*BITMAP_SCALE);
        int height=Math.round(bitmap.getHeight()*BITMAP_SCALE);
        //将缩小的图片作为渲染的图片
        inputBitmap=Bitmap.createScaledBitmap(bitmap,width,height,false);
        // 创建一张渲染后的输出图片。
         outputBitmap=Bitmap.createBitmap(inputBitmap);
        // 创建RenderScript内核对象
        RenderScript rs=RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur  blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn= Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(BLUR_RADIUS*percent/100);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }


    public void setPercent(int percent){

        this.percent=percent;
    }


    public void clear(){
        if(inputBitmap != null && !inputBitmap.isRecycled()){
            inputBitmap.recycle();
            inputBitmap = null;
        }
        if(outputBitmap != null && !outputBitmap.isRecycled()){
            outputBitmap.recycle();
            outputBitmap = null;
        }
    }






}
