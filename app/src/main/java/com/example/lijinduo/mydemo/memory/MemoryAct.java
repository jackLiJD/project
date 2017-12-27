package com.example.lijinduo.mydemo.memory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/12/26
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MemoryAct extends BaseActivity {
    @BindView(R.id.button10)
    Button button10;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.button13)
    Button button13;
    @BindView(R.id.button14)
    Button button14;
    @BindView(R.id.button15)
    Button button15;
    Bitmap bitmap;
    LottieAnimationView animation_view_click;
    Thread thread;
    Context context=MemoryAct.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_memory);
        ButterKnife.bind(this);
        animation_view_click=(LottieAnimationView)findViewById(R.id.animation_view_click);
        LottieComposition.fromAssetFileName(context, "LottieLogo1.json", new LottieComposition.OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view_click.setComposition(composition);
                animation_view_click.setProgress(0.333f);
                animation_view_click.playAnimation();
            }
        });

    }

    @OnClick({R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button10:
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.andes);
                break;
            case R.id.button11:
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pop_nogain);
                break;
            case R.id.button12:
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                    System.gc();
                }
                break;
            case R.id.button13:
                startAnima();
                break;
            case R.id.button14:
                stopAnima();
                break;
            case R.id.button15:
                thread=new Thread(){

                    @Override
                    public void run() {
                    }
                };
                thread.start();
                break;
        }
    }

    /*
  * 开始动画
  */
    private  void startAnima(){

        boolean inPlaying = animation_view_click.isAnimating();
        if (!inPlaying) {
            animation_view_click.setProgress(0f);
            animation_view_click.playAnimation();
        }
    }
    /*
    * 停止动画
    */
    private  void stopAnima(){
        boolean inPlaying = animation_view_click.isAnimating();
        if (inPlaying) {
            animation_view_click.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation_view_click.cancelAnimation();
    }
}
