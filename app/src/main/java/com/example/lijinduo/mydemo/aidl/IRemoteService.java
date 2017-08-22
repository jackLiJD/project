package com.example.lijinduo.mydemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lijinduo.mydemo.IImoocAIDL;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/22
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class IRemoteService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IImoocAIDL.Stub(){
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.e("TAG","收到了来自客户端的请求" + num1 + "+" + num2 );
            return num1 + num2;
        }
    };
}
