package com.example.lijinduo.mydemo.redbag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.databinding.ActRedbagBinding;
import com.example.lijinduo.mydemo.tool.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/13
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RedBagRainMvvm {
    private ActRedbagBinding binding;
    private Context context;
    private List<Integer> list = new ArrayList<>();
    private Bitmap bmp;

    public RedBagRainMvvm(ActRedbagBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
//        AlreadyTakePart();
        initData();
        NoTakePart();
    }

    private void initData() {
        list.add(100);
        list.add(70);
        list.add(10);
        binding.redBag.setProbabilityList(list);
        binding.redBag.setSelectNum(new Rain.SelectNumFace() {
            @Override
            public void select(int num) {
                if (num == 0) {
                    context.startActivity(new Intent(context, RedBagNoGainAct.class));
                } else {
                    Intent intent = new Intent(context, RedBagGainAct.class);
                    intent.putExtra("number", num);
                    context.startActivity(intent);
                }
                AppManager.getAppManager().finishActivity(RedBagAct.class);
            }
        });

    }


    /**
     * 已经参加过红包活动
     */
    private void AlreadyTakePart() {
        binding.redBagBg.setImageResource(R.drawable.already_bg);
        binding.redBagBottomBg.setImageResource(R.drawable.already_bottom);
        binding.redbgTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
            }
        });
        binding.redbgBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 未参加红包活动
     */
    private void NoTakePart() {
        Glide.with(context).load(R.drawable.noalready_bg).into(binding.redBagBg);
        binding.redBagBottomBg.setImageResource(R.drawable.noalready_bottom);
        binding.redbgTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.redBagHome.setVisibility(View.GONE);
                binding.redBag.setVisibility(View.VISIBLE);
            }
        });
        binding.redbgBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(RedBagAct.class);
            }
        });
    }

    /**
     * @param v 活动规则
     */
    public void rule(View v) {
        Toast.makeText(context, "Rule", Toast.LENGTH_SHORT).show();
    }

    /**
     * @param v 活动分享
     */
    public void share(View v) {
        Toast.makeText(context, "share", Toast.LENGTH_SHORT).show();
    }

}
