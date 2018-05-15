package com.example.lijinduo.mydemo.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/5/3
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class TianQiNewBean {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   public List<tianqineibean> getHourly() {
        return hourly;
    }

    public void setHourly(List<tianqineibean> hourly) {
        this.hourly = hourly;
    }

    private List<tianqineibean> hourly=new ArrayList<>();


    public  class tianqineibean{

        private String text;
        private int code;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
