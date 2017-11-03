package com.example.lijinduo.mydemo.view;

import java.io.InputStream;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/11/1
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RamObject {
    private String httpFlag="";
    private InputStream stream;
    private int inputStreamSize = 0;

    public int getInputStreamSize() {
        return inputStreamSize;
    }

    public void setInputStreamSize(int inputStreamSize) {
        this.inputStreamSize = inputStreamSize;
    }

    public InputStream getStream() {
        return stream;
    }
    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    public String getHttpFlag() {
        return httpFlag;
    }

    public void setHttpFlag(String httpFlag) {
        this.httpFlag = httpFlag;
    }
}
