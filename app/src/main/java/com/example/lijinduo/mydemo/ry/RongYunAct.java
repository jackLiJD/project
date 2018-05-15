package com.example.lijinduo.mydemo.ry;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lijinduo.mydemo.BaseActivity;
import com.example.lijinduo.mydemo.R;
import com.example.lijinduo.mydemo.tool.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/10
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RongYunAct extends BaseActivity {
    @BindView(R.id.button17)
    Button button17;
    @BindView(R.id.button18)
    Button button18;
    @BindView(R.id.button19)
    EditText button19;
    @BindView(R.id.button20)
    Button button20;
    @BindView(R.id.button16)
    EditText button16;
    @BindView(R.id.button21)
    Button button21;
    @BindView(R.id.button22)
    Button button22;
    @BindView(R.id.button23)
    Button button23;
    private Context context = RongYunAct.this;
    private String taolunzuId = "99fd03ed-0e2e-4b3d-8a24-3bbd9c393988";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rongyun);
        ButterKnife.bind(this);
    }

    @Override
    public void doSmoething() {

    }


    @OnClick({R.id.button17, R.id.button18, R.id.button20, R.id.button21, R.id.button22,R.id.button23})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button17:
                RongYunTool.getToken(context, button16.getText().toString(), "名字", "");
                break;
            case R.id.button18:
                RongYunTool.liebiao(context, false);
                break;
            case R.id.button20:
                RongYunTool.danliao(context, button19.getText().toString(), "绘画标题");
                break;
            case R.id.button21:
                List<String> list = new ArrayList<>();
                list.add("100");
                list.add("111");
                list.add("112");
                RongYunTool.creatAndJoinTaoLunZu(context, list, "测试的讨论组", new RongIMClient.CreateDiscussionCallback() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d("讨论组id", "onSuccess: " + s);
                        ToastUtils.showToast("创建成功" + s);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case R.id.button22:
                RongYunTool.startTaoLunZu(context, taolunzuId, "讨论组名字");
                break;
            case R.id.button23:
                RongYunTool.liebiao1(context, Conversation.ConversationType.DISCUSSION);
                break;

        }
    }


}
