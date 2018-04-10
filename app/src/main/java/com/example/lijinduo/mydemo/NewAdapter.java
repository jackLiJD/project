package com.example.lijinduo.mydemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class NewAdapter extends BaseQuickAdapter<NewBean,BaseViewHolder>{

    public NewAdapter() {
        super(R.layout.item_newadapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewBean item) {
        helper.setText(R.id.new_item_textview,item.getName());

    }
}
