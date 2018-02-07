package com.example.lijinduo.mydemo.view;

import me.tatarka.bindingcollectionadapter.*;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/2
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public interface ItemViewSelector<T> {
    /**
     * Called on each item in the collection, allowing you to modify the given {@link me.tatarka.bindingcollectionadapter.ItemView}.
     * Note that you should not do complex processing in this method as it's called many times.
     */
    void select(ItemView itemView, int position, T item);

    /**
     * Return the number of <em>different</em> layouts that you will be displaying. Currently this
     * is only used in {@link BindingListViewAdapter}.
     */
    int viewTypeCount();
}
