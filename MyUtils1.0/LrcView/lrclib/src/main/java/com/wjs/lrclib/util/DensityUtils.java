package com.wjs.lrclib.util;

import android.content.Context;
import android.util.TypedValue;
public class DensityUtils
{  
    private DensityUtils()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    /** 
     * dpתpx 
     *  
     * @param context 上下文对象
     * @param dpVal dpVal值
     * @return  转换后的值
     */  
    public static int dp2px(Context context, float dpVal)
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,  
                dpVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * spתpx 
     *  
     * @param context 上下文对象
     * @param spVal spVal值
     * @return  转换后的值
     */  
    public static int sp2px(Context context, float spVal)  
    {  
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,  
                spVal, context.getResources().getDisplayMetrics());  
    }  
  
    /** 
     * pxתdp 
     *  
     * @param context 上下文对象
     * @param pxVal px值
     * @return  转换后的值
     */  
    public static float px2dp(Context context, float pxVal)  
    {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (pxVal / scale);  
    }  
  
    /** 
     * pxתsp 
     *  
     * @param context 上下文对象
     * @param pxVal px值
     * @return  转换后的值
     */  
    public static float px2sp(Context context, float pxVal)  
    {  
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);  
    }  
}