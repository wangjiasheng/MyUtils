package com.wjs.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjs.annotation.ViewClick;
import com.wjs.annotation.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by WJS on 2016/11/21.
 */

public class FragmentUtils {
    public static View inject(Fragment mContext, int layout)
    {
        View view= LayoutInflater.from(mContext.getContext()).inflate(layout,null);
        inject(mContext,view);
        return view;
    }
    public static View inject(Fragment mContext,View view)
    {
        try
        {
            initFragField(mContext,view);
            initFragmentListener(mContext,view);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return view;
    }
    public static View inject(Fragment mContext, LayoutInflater inflater, ViewGroup group, int layout)
    {
        View view= inflater.inflate(layout,group,false);
        inject(mContext,view);
        return view;
    }
    public static void unInject(Fragment fragemnt,View view)
    {
        try
        {
            releaseFragmentField(fragemnt);
            releaseFragmentListener(fragemnt,view);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }
    private static void initFragField(Fragment activity,View view)
    {
        for (Field field : getDeclareFields(activity))
        {
            Class<?> fieldType = field.getType();
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() || fieldType.isArray())
            {
                continue;
            }
            if (isExistAnnotation(field, ViewInject.class))
            {
                Class<?> clazz=activity.getClass().getSuperclass();//不装载父类
                ViewInject mInjectView = field.getAnnotation(ViewInject.class);
                if(mInjectView!=null) {
                    field.setAccessible(true);
                    try {
                        field.set(activity, view.findViewById(mInjectView.value()));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static void releaseFragmentField(Fragment activity)
    {
        for (Field field : getDeclareFields(activity))
        {
            Class<?> fieldType = field.getType();
            if (Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() )
            {
                continue;
            }
            if (isExistAnnotation(field, ViewInject.class))
            {
                field.setAccessible(true);
                try
                {
                    field.set(activity,null);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void initFragmentListener(final Fragment activity,View contentView)
    {
        Method[] methods = getDeclareMethods(activity);
        for (final Method method : methods)
        {
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (isExistAnnotation(method, ViewClick.class))
            {
                ViewClick mViewClick = method.getAnnotation(ViewClick.class);
                if(mViewClick!=null)
                {
                    int array[]=mViewClick.value();
                    if(array!=null) {
                        for (int i = 0; i < array.length; i++) {
                            int viewarrayitemid = array[i];
                            if (viewarrayitemid != -1) {
                                View view = contentView.findViewById(viewarrayitemid);
                                if (view != null) {
                                    method.setAccessible(true);
                                    view.setOnClickListener(
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    try {
                                                        method.invoke(activity, new Object[]{v});
                                                    } catch (IllegalArgumentException e) {
                                                        e.printStackTrace();
                                                    } catch (IllegalAccessException e) {
                                                        e.printStackTrace();
                                                    } catch (InvocationTargetException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void releaseFragmentListener(final Fragment activity,View contentView)
    {
        Method[] methods = getDeclareMethods(activity);
        for (final Method method : methods)
        {
            if (Modifier.isStatic(method.getModifiers()) || !Modifier.isPrivate(method.getModifiers())) {
                continue;
            }
            if (isExistAnnotation(method, ViewClick.class))
            {
                ViewClick mViewClick = method.getAnnotation(ViewClick.class);
                int[] viewId = mViewClick.value();
                if(viewId!=null) {
                    for (int i = 0; i < viewId.length; i++) {
                        if(contentView==null)
                        {
                            contentView=activity.getView();
                            if(contentView!=null)
                            {
                                contentView.findViewById(viewId[i]).setOnClickListener(null);
                            }
                        }
                        else
                        {
                            contentView.findViewById(viewId[i]).setOnClickListener(null);
                        }
                    }
                }
            }
        }
    }
    private static Field[] getDeclareFields(Object obj)
    {
        return obj.getClass().getDeclaredFields();
    }
    private static Method[] getDeclareMethods(Object obj)
    {
        return obj.getClass().getDeclaredMethods();
    }
    private static boolean isExistAnnotation(AccessibleObject obj, Class<? extends Annotation> clazz)
    {
        return obj.isAnnotationPresent(clazz);
    }
}
