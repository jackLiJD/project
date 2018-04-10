package com.example.lijinduo.mydemo.Router;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/3/12
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class RouterParcelable implements Parcelable {
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    public RouterParcelable() {
    }

    protected RouterParcelable(Parcel in) {
        this.title = in.readString();
    }

    public static final Parcelable.Creator<RouterParcelable> CREATOR = new Parcelable.Creator<RouterParcelable>() {
        @Override
        public RouterParcelable createFromParcel(Parcel source) {
            return new RouterParcelable(source);
        }

        @Override
        public RouterParcelable[] newArray(int size) {
            return new RouterParcelable[size];
        }
    };
}
