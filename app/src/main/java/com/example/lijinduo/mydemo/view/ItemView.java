package com.example.lijinduo.mydemo.view;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/2
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
import android.support.annotation.LayoutRes;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public final class ItemView {
    public static final int BINDING_VARIABLE_NONE = 0;
    private int bindingVariable;
    @LayoutRes
    private int layoutRes;
    private Map<Integer, Object> variable;
    private ItemView.OnItemClickListener listener;

    public ItemView() {
    }

    public static me.tatarka.bindingcollectionadapter.ItemView of(int bindingVariable, @LayoutRes int layoutRes) {
        return (new me.tatarka.bindingcollectionadapter.ItemView()).setBindingVariable(bindingVariable).setLayoutRes(layoutRes);
    }

    public ItemView set(int bindingVariable, @LayoutRes int layoutRes) {
        this.bindingVariable = bindingVariable;
        this.layoutRes = layoutRes;
        return this;
    }

    public ItemView setBindingVariable(int bindingVariable) {
        this.bindingVariable = bindingVariable;
        return this;
    }

    public ItemView setLayoutRes(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }

    public ItemView setVariable(Integer variableId, Object value) {
        if(this.variable == null) {
            this.variable = new HashMap();
        }

        this.variable.put(variableId, value);
        return this;
    }

    public ItemView setOnItemClickListener(ItemView.OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public int bindingVariable() {
        return this.bindingVariable;
    }

    @LayoutRes
    public int layoutRes() {
        return this.layoutRes;
    }

    public Map<Integer, Object> variable() {
        return this.variable;
    }

    public OnItemClickListener listener() {
        return this.listener;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
           ItemView itemView = (ItemView)o;
            return this.bindingVariable != itemView.bindingVariable?false:this.layoutRes == itemView.layoutRes;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.bindingVariable;
        result = 31 * result + this.layoutRes;
        return result;
    }

    public interface OnItemClickListener {
        void onItemClick(View var1, int var2);
    }
}
