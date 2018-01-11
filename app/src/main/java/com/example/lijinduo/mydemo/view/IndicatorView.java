package com.example.lijinduo.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lijinduo.mydemo.R;


/**
 * Created by lijinduo on 2017/3/22.
 */

public class IndicatorView extends RelativeLayout {
    private Context context;
    private ImageView imageView;
    private RelativeLayout.LayoutParams layoutParams;
    private int diameter;
    private int space;


    public IndicatorView(Context context) {
        super(context);
        this.context = context;
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void attribute(int pageSize, int diameter, int space) {
        this.space=space;
        this.diameter=diameter;
        diameter = dip2px(context, diameter);
        space = dip2px(context, space);
        for (int i = 0; i < pageSize; i++) {
            ImageView imageView = new ImageView(context);
            addView(imageView);
            RelativeLayout.LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.width = diameter;
            layoutParams.height = diameter;
            layoutParams.setMargins(i * (space + diameter), 0, 0, 0);
            imageView.setBackgroundResource(R.drawable.guide_ball);
            imageView.getBackground().setAlpha(38);
            imageView.setLayoutParams(layoutParams);
        }
        imageView = new ImageView(context);
        addView(imageView);
        layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = diameter;
        layoutParams.height = diameter;
        imageView.setBackgroundResource(R.drawable.guide_ball_select);
        imageView.setLayoutParams(layoutParams);
    }


    public void moveView(int position, float positionOffset) {
        Log.d("moveView", position+"moveView: "+positionOffset);
        layoutParams.setMargins((dip2px(context, (position + positionOffset) * (space+diameter))), 0, 0, 0);
        imageView.setLayoutParams(layoutParams);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
