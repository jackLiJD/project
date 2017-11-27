package com.example.lijinduo.mydemo.mvvm;

import android.app.Activity;
import android.util.Log;

import com.example.lijinduo.mydemo.BR;
import com.example.lijinduo.mydemo.R;

import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/20
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MvvmActNeiVM extends BaseRecyclerViewVM<inBean> {
    public MvvmActNeiVM(List<inBean> inBeanList) {
        items.addAll(inBeanList);
    }

    @Override
    protected void selectView(ItemView itemView, int position, inBean item) {
        Log.d("selectViewIN", "selectView: ");
        itemView.set(BR.item, R.layout.item_mvvm_nei);
    }
}
