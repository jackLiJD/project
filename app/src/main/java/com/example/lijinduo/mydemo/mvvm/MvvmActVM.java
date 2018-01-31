package com.example.lijinduo.mydemo.mvvm;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lijinduo.mydemo.BR;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.databinding.ItemMvvmBinding;
import com.example.lijinduo.mydemo.retrofit.InvestListBean;
import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class MvvmActVM extends BaseRecyclerViewVM<OutBean>{
    public  ObservableField<InvestListBean> investListBeanObservableField = new ObservableField<>();
    public List<OutBean> outBeans=new ArrayList<>();
    private Activity context;
//    private ItemMvvmBinding binding;
    public MvvmActVM(Activity context) {
        this.context=context;
        data();
    }

    private void data() {
        for (int i = 0; i <30 ; i++) {
            OutBean outBean=new OutBean();
            outBean.outTitle="外部"+i;
            for (int j = 0; j < 3; j++) {
                inBean inbean=new inBean();
                inbean.inTitle="内部"+j;
                outBean.inBeanList.add(inbean);
            }
            outBeans.add(outBean);
        }
        items.addAll(outBeans);

    }


    @Override
    protected void selectView(ItemView itemView, int position, OutBean item) {
        Log.d("selectViewOut", "selectView: ");
        itemView.set(BR.item, R.layout.item_mvvm);




//        ItemMvvmBinding binding= DataBindingUtil.setContentView(context,layoutRes);
//        ItemMvvmBinding binding= DataBindingUtil.bind(context.getWindow().getDecorView());
//        ItemMvvmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes,(ViewGroup) context.getWindow().getDecorView() , true);
//        MvvmActNeiVM mvvmnei=new MvvmActNeiVM(item.inBeanList);
//        binding.setViewModel(mvvmnei);
    }
}
