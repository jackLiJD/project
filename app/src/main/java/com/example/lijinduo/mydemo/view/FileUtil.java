package com.example.lijinduo.mydemo.view;

import android.text.TextUtils;

import java.io.File;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/31
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class FileUtil {

    public static void deleteDirs(String path,boolean isDeleteDir){

        if (TextUtils.isEmpty(path)){
            return;
        }
        File dir = new File(path);
        if (!dir.exists()){
            return;
        }
        File [] files = dir.listFiles();
        for ( File file :  files){
            if ( file.isDirectory()){
                deleteDirs(file.getAbsolutePath(),isDeleteDir);
            }else{
                file.delete();
            }
        }
        if (isDeleteDir){
            dir.delete();
        }

    }
}
