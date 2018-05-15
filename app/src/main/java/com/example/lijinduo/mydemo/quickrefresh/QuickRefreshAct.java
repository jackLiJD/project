package com.example.lijinduo.mydemo.quickrefresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.NewAdapter;
import com.example.lijinduo.mydemo.NewBean;
import com.example.lijinduo.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/2/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class QuickRefreshAct extends BaseActivity implements    SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.quick_recyclerview)
    RecyclerView quickRecyclerview;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private List<NewBean> list = new ArrayList<>();
    private int position;
    private NewAdapter adapter;
    private Context mContext = QuickRefreshAct.this;
    /**
     * 当前页数和总页数
     */
    private int currentPage,totalPage=3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (null != swiperefresh && swiperefresh.isRefreshing()) {
                swiperefresh.setRefreshing(false);
                // 下拉刷新后可以上拉加载
                adapter.setEnableLoadMore(true);
            }
            if (null != adapter && adapter.isLoading()) {
                // 上拉加载后可以下拉刷新
                swiperefresh.setEnabled(true);
            }
            if (msg .what==0x123) {
                adapter.addData(list);
                if (currentPage == totalPage) {
                    adapter.loadMoreEnd();
                }else{
                    adapter.loadMoreComplete();
                }
            }
            if (msg .what==0x234) {
                adapter.setNewData(list);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quick_refresh);
        ButterKnife.bind(this);
        initView();
        initData();
        adapter.addData(list);
    }

    private void initView() {
        swiperefresh.setOnRefreshListener(this);
        adapter = new NewAdapter();
        quickRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        quickRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        quickRecyclerview.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, quickRecyclerview);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewBean newBean = (NewBean) adapter.getData().get(position);
                Toast.makeText(mContext, newBean.getName(), 200).show();
            }
        });

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            position++;
            NewBean newBean = new NewBean();
            newBean.setName("Text显示文本" + position);
            list.add(newBean);
        }

    }

    @Override
    public void onLoadMoreRequested() {
        currentPage++;
        initData();
        // 防止上拉加载的时候可以下拉刷新
        swiperefresh.setEnabled(false);
        handler.sendEmptyMessageDelayed(0x123, 500);

    }

    @Override
    public void onRefresh() {
        currentPage=0;
        position=0;
        initData();
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        adapter.setEnableLoadMore(false);
        handler.sendEmptyMessageDelayed(0x234, 1000);
    }
    @Override
    public void doSmoething() {

    }
}
