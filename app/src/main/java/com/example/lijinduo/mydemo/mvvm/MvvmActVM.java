package com.example.lijinduo.mydemo.mvvm;

import android.app.Activity;
import android.databinding.ObservableField;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import com.example.lijinduo.mydemo.BR;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.retrofit.InvestListBean;
import com.example.lijinduo.mydemo.tool.AppManager;
import com.example.lijinduo.mydemo.tool.MyApplication;

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
        for (int i = 0; i <80 ; i++) {
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
    private Handler handler=new Handler();
    //获取item上的view
    //适配问题 最后一项bug  (已解决)
    //item嵌套item
    @Override
    protected void selectView(ItemView itemView, int position, OutBean item) {
        Log.d("selectViewOut", "selectView: "+position);
//        itemView.set(BR.item, R.layout.item_mvvm);
        itemView.setBindingVariable(BR.item);
        itemView.setLayoutRes(R.layout.item_mvvm);
//        ItemMvvmBinding binding=DataBindingUtil.getBinding(this);
//        binding.mvvmtv.setText("asdasdsadas");



        handler.post(new Runnable() {
            @Override
            public void run() {
                MyApplication.scaleScreenHelper.loadView((ViewGroup) AppManager.getAppManager().currentActivity().getWindow().getDecorView());
            }
        });




//        ItemMvvmBinding binding= DataBindingUtil.bind(context.getWindow().getDecorView());
//        ItemMvvmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes,(ViewGroup) context.getWindow().getDecorView() , true);
//        MvvmActNeiVM mvvmnei=new MvvmActNeiVM(item.inBeanList);
//        binding.setViewModel(mvvmnei);
    }
}
