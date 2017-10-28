package com.wjs.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class PermissionUtils
{
        /**
         * @author 王家胜
         * @param context 上下文对象
         * @param permissionstring 权限名称
         * @param packageName 应用的包名
         * @return 是否拥有该权限
         */
        public static boolean hasPermission(Context context,String permissionstring,String packageName)
        {
                PackageManager pm = context.getPackageManager();
                boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permissionstring, packageName));
                if (permission)
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }
}
