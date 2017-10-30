package com.wjs.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 常用单位转换的辅助类 
 *
 *
 *
 */
public class DensityUtils
{
    private DensityUtils()
    {  
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px 
     *
     * @param context 暂无
     * @param dpVal 暂无
     * @return 暂无
     */
    public static int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px 
     *
     * @param context  暂无
     * @param spVal 暂无
     * @return 暂无
     */
    public static int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp 
     *
     * @param context 暂无
     * @param pxVal 暂无
     * @return 暂无
     */
    public static float px2dp(Context context, float pxVal)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp 
     *
     * @param context  暂无
     * @param pxVal  暂无
     * @return 暂无
     */
    public static float px2sp(Context context, float pxVal)
    {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}  