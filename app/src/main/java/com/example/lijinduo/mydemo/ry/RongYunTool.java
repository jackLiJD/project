package com.example.lijinduo.mydemo.ry;

import android.content.Context;
import android.util.Log;

import com.example.lijinduo.mydemo.retrofit.IWeather;
import com.example.lijinduo.mydemo.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import retrofit2.Retrofit;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/11
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RongYunTool {

    /**
     * 单聊洁面
     */
    public static void danliao(Context context,String id,String title) {
        RongIM.getInstance().startPrivateChat(context, id, title);
    }

    /**
     * @param context
     * @param conversationType
     * 查看列表页面
     */
    public static void liebiao1(Context context, Conversation.ConversationType conversationType){
        RongIM.getInstance().startSubConversationList(context,conversationType);
    }


    /**
     * 查看聊天列表
     */
    public static void liebiao(Context context,boolean booean) {
        Map<String, Boolean> supportedConversation = new HashMap<>();
        supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), booean);
        supportedConversation.put(Conversation.ConversationType.DISCUSSION.getName(), booean);
        RongIM.getInstance().startConversationList(context, supportedConversation);
    }
    /**
     * 创建并加入讨论组
     */
    public static void creatAndJoinTaoLunZu(Context context, final List<String> targetUserIds, final String title,final RongIMClient.CreateDiscussionCallback callback){
        RongIM.getInstance().createDiscussionChat(context, targetUserIds,title,callback);
    }

    /**
     * 打开讨论组聊天窗口
     */
    public static void startTaoLunZu(Context context,String taolunzuId,String title){
        RongIM.getInstance().startDiscussionChat(context, taolunzuId, title);
    }


    /**
     * 获取token
     */
    public static void getToken(final Context context, String uid, String name, String headPath) {
        RetrofitClient.baseUrl="http://api.cn.ronghub.com/";
        RetrofitClient.getInstance().updataRetrofit();
        RetrofitClient.getInstance().getService(IWeather.class).getToken(uid, name, headPath).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RongYunBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(RongYunBean value) {
                connect(context,value.getToken());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                RetrofitClient.baseUrl="http://tj.nineton.cn/?city=CHSH000000";
                RetrofitClient.getInstance().updataRetrofit();
            }
        });
    }


    private static void connect(Context context,String token) {

        if (context.getApplicationInfo().packageName.equals(getCurProcessName(context.getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("融云用户id", "--onSuccess" + userid);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                }
            });
        }
    }
}
