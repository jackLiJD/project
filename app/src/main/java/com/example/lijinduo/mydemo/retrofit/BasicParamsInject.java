package com.example.lijinduo.mydemo.retrofit;

import android.util.Log;

import java.security.MessageDigest;
import java.util.Arrays;

import okhttp3.Interceptor;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/23
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class BasicParamsInject {
    private static final String CHARSET_NAME = "UTF-8";
    private BasicParamsInterceptor interceptor;

    private BasicParamsInject() {
    }

    public static BasicParamsInject getInstance() {
        return new BasicParamsInject();
    }

    public Interceptor getInterceptor() {
                if (interceptor == null) {
                    upDataInterceptor();
        }
        return interceptor;
    }

    public void upDataInterceptor() {
      String time=  System.currentTimeMillis()+"";
        Log.d("我要的时间", "upDataInterceptor: "+time);


        interceptor = new BasicParamsInterceptor.Builder()
                //公共key和value
                .addParam("keys", "values")
                .addHeaderParam("App-Key","mgb7ka1nm4c8g")
                .addHeaderParam("Nonce",time)
                .addHeaderParam("Timestamp",time)
                .addHeaderParam("Signature",getSha1("3HQ4lmkMUBT"+time+time))
                .build();

    }

    public  String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }



}