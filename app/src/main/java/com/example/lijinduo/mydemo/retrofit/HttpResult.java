package com.example.lijinduo.mydemo.retrofit;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/8/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class HttpResult {

    /**
     * 数据返回code  如成功 失败 账号被顶 系统维护等操作
     */
    private int resCode;

    /**
     * 数据返回信息
     */
    private String resMsg;

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }





}
