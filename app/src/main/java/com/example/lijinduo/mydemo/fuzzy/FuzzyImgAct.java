package com.example.lijinduo.mydemo.fuzzy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class FuzzyImgAct extends BaseActivity {
    @BindView(R.id.fuzzy_img)
    ImageView fuzzyImg;
    @BindView(R.id.fuzzy_seek)
    SeekBar fuzzySeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fuzzy_img);
        ButterKnife.bind(this);

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pop_nogain);
        final BlurBitmap bb = new BlurBitmap();
        fuzzySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress>0) {
                    bb.setPercent(progress);
                    fuzzyImg.setImageBitmap(bb.blur(FuzzyImgAct.this, bitmap));
                }else{
                    fuzzyImg.setImageBitmap(bitmap);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
