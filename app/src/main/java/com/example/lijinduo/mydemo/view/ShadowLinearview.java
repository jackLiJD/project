package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.R;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/19
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ShadowLinearview extends LinearLayout {
    private Paint mPaint;
    RectF rect;
    String str="曾经也想过\n" +
            "你早晚离开我\n" +
            "当你躲避我拥抱的时候\n" +
            "是我太懦弱也早该明白的\n" +
            "你怎么可能信守那承诺\n" +
            "也许我就这样一步一步沦陷\n" +
            "每天独自默默熬过黑夜白天\n" +
            "然后找个怀抱温暖我几天\n" +
            "但是我怎么就忘不掉那昨天\n" +
            "一路走来那些晴天聊天雨天\n" +
            "现在看来都是可笑的敷衍\n" +
            "我想在你身后\n" +
            "即使你不爱我\n" +
            "这样就不会再害怕寂寞\n" +
            "我无法逃脱\n" +
            "选择那种没有你的以后\n" +
            "相信我 我一定能 把你忘记的\n" +
            "也许你就这样一步一步走远\n" +
            "从此我们再也没有任何关联\n" +
            "人长大都要学会事过境迁\n" +
            "但是我怎么就看不清你的脸\n" +
            "眼里有我无法捕捉到的伤悲\n" +
            "没有我你的生活哪有差别\n" +
            "我想在你身后\n" +
            "即使你不爱我\n" +
            "这样就不会再害怕寂寞\n" +
            "我无法逃脱\n" +
            "选择那种没有你的以后\n" +
            "相信我 我一定能 把你忘记的\n" +
            "我想在你身后\n" +
            "即使你不爱我\n" +
            "这样就不会再害怕寂寞\n" +
            "我无法逃脱\n" +
            "选择那种没有你的以后\n" +
            "相信我 我一定能 把你忘记的";
    public ShadowLinearview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShadowLayer(50, 15F, 15F, Color.LTGRAY);
        canvas.drawRect(rect,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         rect = new RectF(0 , 0, getWidth()-15f, getHeight()-15f);
    }
}
