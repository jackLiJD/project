package com.example.lijinduo.mydemo.magnifyingglass;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.view.MagnifyingGlassView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/6
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MagnifyingGlassAct extends BaseActivity {


    @BindView(R.id.src)
    Button src;
    @BindView(R.id.dst)
    Button dst;
    @BindView(R.id.srcover)
    Button srcover;
    @BindView(R.id.dstover)
    Button dstover;
    @BindView(R.id.srcin)
    Button srcin;
    @BindView(R.id.dstin)
    Button dstin;
    @BindView(R.id.srcout)
    Button srcout;
    @BindView(R.id.dstout)
    Button dstout;
    @BindView(R.id.srcatop)
    Button srcatop;
    @BindView(R.id.dstatop)
    Button dstatop;
    @BindView(R.id.xor)
    Button xor;
    @BindView(R.id.darken)
    Button darken;
    @BindView(R.id.lighten)
    Button lighten;
    @BindView(R.id.multiply)
    Button multiply;
    @BindView(R.id.screen)
    Button screen;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.magnifyingglass)
    MagnifyingGlassView magnifyingglass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_magnifying_glass);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.src, R.id.dst, R.id.srcover, R.id.dstover, R.id.srcin, R.id.dstin, R.id.srcout, R.id.dstout, R.id.srcatop, R.id.dstatop, R.id.xor, R.id.darken, R.id.lighten, R.id.multiply, R.id.screen, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.src:
                magnifyingglass.setType(PorterDuff.Mode.SRC);
                break;
            case R.id.dst:
                magnifyingglass.setType(PorterDuff.Mode.DST);
                break;
            case R.id.srcover:
                magnifyingglass.setType(PorterDuff.Mode.SRC_OVER);
                break;
            case R.id.dstover:
                magnifyingglass.setType(PorterDuff.Mode.DST_OVER);
                break;
            case R.id.srcin:
                magnifyingglass.setType(PorterDuff.Mode.SRC_IN);
                break;
            case R.id.dstin:
                magnifyingglass.setType(PorterDuff.Mode.DST_IN);
                break;
            case R.id.srcout:
                magnifyingglass.setType(PorterDuff.Mode.SRC_OUT);
                break;
            case R.id.dstout:
                magnifyingglass.setType(PorterDuff.Mode.DST_OUT);
                break;
            case R.id.srcatop:
                magnifyingglass.setType(PorterDuff.Mode.SRC_ATOP);
                break;
            case R.id.dstatop:
                magnifyingglass.setType(PorterDuff.Mode.DST_ATOP);
                break;
            case R.id.xor:
                magnifyingglass.setType(PorterDuff.Mode.XOR);
                break;
            case R.id.darken:
                magnifyingglass.setType(PorterDuff.Mode.DARKEN);
                break;
            case R.id.lighten:
                magnifyingglass.setType(PorterDuff.Mode.LIGHTEN);
                break;
            case R.id.multiply:
                magnifyingglass.setType(PorterDuff.Mode.MULTIPLY);
                break;
            case R.id.screen:
                magnifyingglass.setType(PorterDuff.Mode.SCREEN);
                break;
            case R.id.clear:
                magnifyingglass.setType(PorterDuff.Mode.CLEAR);
                break;
        }
    }
}
