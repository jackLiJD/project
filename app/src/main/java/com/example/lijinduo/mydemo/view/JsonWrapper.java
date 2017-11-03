package com.example.lijinduo.mydemo.view;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/31
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class JsonWrapper extends JSONObject {
    public JsonWrapper() {
        super();
    }
    public JsonWrapper(String jsonString) throws JSONException {
        super(jsonString);
    }

    public String obj2JosnStr(Object obj,Class cls) throws Exception{
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            String name = fs[i].getName();
            fs[i].setAccessible(true);
            String value = (String) fs[i].get(obj);
            this.put(name,value);
        }
        return this.toString();
    }

    public <T> T getBean(Class<T> cls) throws Exception{
        T obj = cls.newInstance();
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            String name = fs[i].getName();
            fs[i].setAccessible(true);
            try {
                Object value = this.get(name);
                if(value==null)continue;
                fs[i].set(obj, value);
            }catch (Exception e){
            }
        }
        return obj;
    }

}
