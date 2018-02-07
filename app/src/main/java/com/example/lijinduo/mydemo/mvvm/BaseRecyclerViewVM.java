package com.example.lijinduo.mydemo.mvvm;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;


/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/3/3 10:45
 * <p/>
 * Description: 抽象的共用RecyclerView
 */
public abstract class BaseRecyclerViewVM<T> {
    /**
     * 设置布局
     */
    protected abstract void selectView(ItemView itemView, int position, T item);


    /**
     * 是否显示appbar
     */
    public final ObservableBoolean hidden = new ObservableBoolean(true);
    /**
     * tips栏目
     */
    public String[] tips = null;

    /**
     * tip标题序号
     */
    public ObservableInt tipNum =new ObservableInt();
    /**
     * 数据源
     */
    public final ObservableList<T> items = new ObservableArrayList<>();
    public final ObservableList<T> itemsNei = new ObservableArrayList<>();

    public final ItemViewSelector<T> itemView = new ItemViewSelector<T>() {
        @Override
        public void select(ItemView itemView, int position, T item) {
            selectView(itemView, position, item);
        }


        @Override
        public int viewTypeCount() {
            return 0;
        }
    };

    public final ItemViewSelector<T> itemViewNei = new ItemViewSelector<T>() {
        @Override
        public void select(ItemView itemView, int position, T item) {


            selectView(itemView, position, item);

        }

        @Override
        public int viewTypeCount() {
            return 0;
        }
    };

}
