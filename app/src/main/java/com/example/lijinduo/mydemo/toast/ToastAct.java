package com.example.lijinduo.mydemo.toast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.DrawView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/9/1
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class ToastAct extends BaseActivity {
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.drawview)
    DrawView drawview;
    @BindView(R.id.button7)
    Button button7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_toast);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button4, R.id.button5, R.id.button6,R.id.button7, R.id.drawview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button4:
                SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                        .setButtonText("UNDO")
                        .setButtonIconResource(R.mipmap.ic_launcher)
//                        .setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
                        .setProgressBarColor(Color.WHITE)
                        .setText("Email deleted")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                        .setAnimations(Style.ANIMATIONS_POP).show();
                break;
            case R.id.button5:
                SuperActivityToast.create(this, new Style(), Style.TYPE_STANDARD)
                        .setButtonText("UNDO")
                        .setButtonIconResource(R.mipmap.ic_launcher)
//                        .setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
                        .setProgressBarColor(Color.WHITE)
                        .setText("Email deleted")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                        .setAnimations(Style.ANIMATIONS_POP).show();
                break;
            case R.id.button6:
                SuperActivityToast.create(this, new Style(), Style.TYPE_PROGRESS_CIRCLE)
                        .setButtonText("UNDO")
                        .setButtonIconResource(R.mipmap.ic_launcher)
//                        .setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
                        .setProgressBarColor(Color.WHITE)
                        .setText("Email deleted")
                        .setDuration(Style.DURATION_LONG)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
                        .setAnimations(Style.ANIMATIONS_POP).show();

                break;
            case R.id.button7:
                break;

        }
    }
    @Override
    public void doSmoething() {

    }
}
