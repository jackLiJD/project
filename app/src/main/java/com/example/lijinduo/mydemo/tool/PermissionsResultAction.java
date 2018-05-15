package com.example.lijinduo.mydemo.tool;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2018/4/24
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private final Set<String> mPermissions = new HashSet(1);
    private Looper mLooper = Looper.getMainLooper();

    public PermissionsResultAction() {
    }

    public PermissionsResultAction(@NonNull Looper looper) {
        this.mLooper = looper;
    }

    public abstract void onGranted();

    public abstract void onDenied(String var1);

    public synchronized boolean shouldIgnorePermissionNotFound(String permission) {
        Log.d(TAG, "Permission not found: " + permission);
        return true;
    }

    @CallSuper
    protected final synchronized boolean onResult(@NonNull String permission, int result) {
        return result == 0?this.onResult(permission, Permissions.GRANTED):this.onResult(permission, Permissions.DENIED);
    }

    @CallSuper
    protected final synchronized boolean onResult(@NonNull final String permission, Permissions result) {
        this.mPermissions.remove(permission);
        if(result == Permissions.GRANTED) {
            if(this.mPermissions.isEmpty()) {
                (new Handler(this.mLooper)).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onGranted();
                    }
                });
                return true;
            }
        } else {
            if(result == Permissions.DENIED) {
                (new Handler(this.mLooper)).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onDenied(permission);
                    }
                });
                return true;
            }

            if(result == Permissions.NOT_FOUND) {
                if(!this.shouldIgnorePermissionNotFound(permission)) {
                    (new Handler(this.mLooper)).post(new Runnable() {
                        public void run() {
                            PermissionsResultAction.this.onDenied(permission);
                        }
                    });
                    return true;
                }

                if(this.mPermissions.isEmpty()) {
                    (new Handler(this.mLooper)).post(new Runnable() {
                        public void run() {
                            PermissionsResultAction.this.onGranted();
                        }
                    });
                    return true;
                }
            }
        }

        return false;
    }

    @CallSuper
    protected final synchronized void registerPermissions(@NonNull String[] perms) {
        Collections.addAll(this.mPermissions, perms);
    }
}
