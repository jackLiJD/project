package com.example.lijinduo.mydemo.sea;

import android.support.v7.widget.LinearLayoutManager;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/18
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public  class Util {
    public static int scrollToMyWantPosition(int position, LinearLayoutManager linearManager ) {
//        int firstItem = linearManager.findFirstCompletelyVisibleItemPosition();
        int lastCompletelyItem = linearManager.findLastCompletelyVisibleItemPosition();
        int firstItem = linearManager.findFirstVisibleItemPosition();
        int lastItem = linearManager.findLastVisibleItemPosition();
        int targetItem = 0;
        if (position < firstItem) {
            targetItem = position - lastItem + firstItem;
            if (lastCompletelyItem != lastItem) {
                targetItem=targetItem+1;
            }
            if (targetItem < 0) {
                targetItem = 0;
            }
            return targetItem;
        } else if (position > lastItem) {
            return position;
        } else {
            targetItem = position - (lastItem - firstItem);
            if (lastCompletelyItem != lastItem) {
                targetItem=targetItem+1;
            }
            if (targetItem < 0) {
                return 0;
            } else {
                return targetItem;
            }

        }


    }
    public static  int scrollByMyWantY(int position,LinearLayoutManager linearLayoutManager,int height){
        return (position-linearLayoutManager.findLastCompletelyVisibleItemPosition())*height;
    }




}
