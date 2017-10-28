package com.wjs.manager;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.wjs.annotation.AutoRun;
import com.wjs.annotation.ViewClick;
import com.wjs.annotation.ViewInject;
import com.wjs.annotation.Backbutton;
import com.wjs.annotation.ContentView;
import com.wjs.annotation.Doubleclickexit;
import com.wjs.annotation.Network;

/**
 * 
 * @author Saifei
 * 
 */
public class LayoutUtils
{
	public static void inject(Activity activity)
	{
		try
		{
			initLayout(activity);//不用优化
			initDoubleClick(activity);//不用优化
			initFields(activity);
			initMethods(activity);
			initSuperMethods(activity);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	private static void initFields(Activity activity)
	{
		for (Field field : getDeclareFields(activity))
		{
			initField(activity,field);
			initBackButton(activity,field);
		}
	}
	public static void initMethods(Activity activity)
	{
		Method[] methods = getDeclareMethods(activity);
		for (final Method method : methods) {
			initListener(activity,method);//Methdo
		}
	}
	public static void initSuperMethods(Activity activity)
	{
		Method[] methods = getSuperDeclareMethods(activity.getClass());
		for (final Method method : methods)
		{
			initAutoRun(activity,method);
		}
	}
	public static void initAutoRun(Activity activity,Method method)
	{
		if (Modifier.isStatic(method.getModifiers())) {
			return;
		}
		if (isExistAnnotation(method, AutoRun.class))
		{
			AutoRun mViewClick = method.getAnnotation(AutoRun.class);
			if(mViewClick!=null)
			{
				try
				{
					method.invoke(activity, new Object[]{});
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static void initLayout(Activity activity)
	{
		try {
			Class clazz = activity.getClass();
			if (clazz.isAnnotationPresent(ContentView.class))
			{
				Method method=getSuperMathod(activity.getClass(),"setContentView",int.class);
				if(method!=null)
				{
					ContentView mViewClick = activity.getClass().getAnnotation(ContentView.class);
					if(mViewClick!=null)
					{
						int value=mViewClick.value();
						if(value!=-1)
						{
							method.invoke(activity,new Object[]{value});
						}
					}
				}
			}
		}catch (InvocationTargetException e)
		{
		}catch (IllegalAccessException e) {
		}
	}

	/**
	 * 不需要优化
	 * @param actiity
     */
	private static void initDoubleClick(Activity actiity)
	{
		try
		{
			Class cla=actiity.getClass();
			if(cla.isAnnotationPresent(Doubleclickexit.class))
			{
				Method method=getSuperMathod(cla,"setDoubleClickExit",boolean.class);
				if (method != null) {
					method.invoke(actiity, new Object[]{true});
				}
			}
		}
		catch (InvocationTargetException e) {
		} catch (IllegalAccessException e) {
		}
	}
	private static Method getSuperMathod(Class clazz,String method,Class<?>... parameterTypes)
	{
		try
		{
			return clazz.getDeclaredMethod(method,parameterTypes);
		}
		catch (NoSuchMethodException e)
		{
			return clazz.getSuperclass()==null?null:getSuperMathod(clazz.getSuperclass(),method,parameterTypes);
		}
	}
	public static Method[] getSuperDeclareMethods(Class object)
	{
		ArrayList<Method> methods=new ArrayList<Method>();
		do
		{
			Method method[]=object.getDeclaredMethods();
			for(int i=0;i<method.length;i++)
			{
				methods.add(method[i]);
			}
		}
		while((object=object.getSuperclass())!=null);
		return methods.toArray(new Method[]{});
	}
	private static void initListener(final Activity activity,final Method method)
	{
			if (Modifier.isStatic(method.getModifiers())) {
				return;
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
								View view = activity.findViewById(viewarrayitemid);
								if (view != null) {
									method.setAccessible(true);
									view.setOnClickListener(
											new OnClickListener() {
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
	private static Method[] getDeclareMethods(Object obj)
	{
		return obj.getClass().getDeclaredMethods();
	}
	private static void initField(Activity activity,Field field)
	{
		Class<?> fieldType = field.getType();
		if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() || fieldType.isArray())
		{
			return;
		}
		if (isExistAnnotation(field, ViewInject.class))
		{
			Class<?> clazz=activity.getClass().getSuperclass();//不装载父类
			ViewInject mInjectView = field.getAnnotation(ViewInject.class);
			if(mInjectView!=null) {
				field.setAccessible(true);
				try {
					field.set(activity, activity.findViewById(mInjectView.value()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static void initBackButton(final Activity activity, Field field)
	{
			Class<?> fieldType = field.getType();
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() || fieldType.isArray())
			{
				return;
			}
			if (isExistAnnotation(field, Backbutton.class))
			{
				Class<?> clazz=activity.getClass().getSuperclass();//不装载父类
				Backbutton mInjectView = field.getAnnotation(Backbutton.class);
				if(mInjectView!=null) {
					field.setAccessible(true);
					View view=activity.findViewById(mInjectView.value());
					try {
						field.set(activity, view);
					}
					catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					view.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							try
							{
								Method method=getSuperMathod(activity.getClass(),"finish",new Class[]{});
								if(method!=null)
								{
									method.invoke(activity,new Object[]{});
								}
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
	}
	private static Field[] getDeclareFields(Object obj)
	{
		return obj.getClass().getDeclaredFields();
	}
	/**
	 * @author Saifei 变量值
	 * @param clazz  注解class对象
	 * @return 是否是该注解对象
	 */
	private static boolean isExistAnnotation(AccessibleObject obj,Class<? extends Annotation> clazz)
	{
		return obj.isAnnotationPresent(clazz);
	}
    public static void unInject(Activity activity)
	{
		try
		{
			releaseField(activity);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		try
		{
			releaseListener(activity);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		try
		{
			releaseNetwork(activity);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		try {
			releaseBackButton(activity);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	private static void releaseField(Activity activity)
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
	private static void releaseNetwork(Activity activity)
	{
		for (Field field : getDeclareFields(activity))
		{
			Class<?> fieldType = field.getType();
			if (Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() )
			{
				continue;
			}
			if (isExistAnnotation(field, Network.class))
			{
				field.setAccessible(true);
				try
				{
					Object o = field.get(activity);
					if(o!=null)
					{
						try
						{
							Method themed = o.getClass().getDeclaredMethod("cancle", new Class[]{});
							if(themed!=null)
							{
								themed.invoke(o, new Object[]{});
							}
						}
						catch (NoSuchMethodException e)
						{
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							e.printStackTrace();
						}
						field.set(activity,null);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static void releaseListener(final Activity activity)
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
						activity.findViewById(viewId[i]).setOnClickListener(null);
					}
				}
			}
		}
	}
	private static void releaseBackButton(final Activity activity)
	{
		for (Field field : getDeclareFields(activity))
		{
			Class<?> fieldType = field.getType();
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || fieldType.isPrimitive() || fieldType.isArray())
			{
				continue;
			}
			if (isExistAnnotation(field, Backbutton.class))
			{
				Class<?> clazz=activity.getClass().getSuperclass();//不装载父类
				Backbutton mInjectView = field.getAnnotation(Backbutton.class);
				if(mInjectView!=null) {
					field.setAccessible(true);
					View view=activity.findViewById(mInjectView.value());
					try {
						field.set(activity, null);
					}
					catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					view.setOnClickListener(null);
				}
			}
		}
	}
}
