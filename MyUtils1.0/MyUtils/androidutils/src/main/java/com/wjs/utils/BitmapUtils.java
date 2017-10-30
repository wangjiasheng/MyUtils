package com.wjs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public class BitmapUtils
{

	/**
	 * 更具宽高压缩图片
	 * @param context 上下文对象
	 * @param resId 需要压缩的图片的id
	 * @param deswidth 目标的宽度
	 * @param desheight 目标的高度
	 * @return 返回压缩后的图片
	 */
	public static Bitmap compressBitmapTo(Context context,int resId,int deswidth,int desheight)
	{
		BitmapFactory.Options potion=new BitmapFactory.Options();
		potion.inJustDecodeBounds=true;
		BitmapFactory.decodeResource(context.getResources(),resId,potion);
		int outWidth = potion.outWidth;
		int outHeight = potion.outHeight;
		int scale=1;
		if(outWidth>deswidth||outHeight>desheight)
		{
			int scaleX=outWidth/deswidth;
			int scaleY=outHeight/desheight;
			if(scaleX<scaleY)
			{
				scale=scaleY;
			}
			else
			{
				scale=scaleX;
			}
		}
		potion.inSampleSize=scale;
		potion.inJustDecodeBounds=false;
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, potion);
		return bitmap;
	}
	/**
	 * 根据宽缩放
	 * @param context 暂无
	 * @param resId 暂无
	 * @param deswidth 暂无
	 * @return 暂无
	 */
	public static Bitmap compressBitmapWitchWitdh(Context context,int resId,int deswidth)
	{
		BitmapFactory.Options potion=new BitmapFactory.Options();
		potion.inJustDecodeBounds=true;
		BitmapFactory.decodeResource(context.getResources(),resId,potion);
		int outWidth = potion.outWidth;
		int scale=1;
		if(outWidth>deswidth)
		{
			scale=outWidth/deswidth;
		}
		potion.inSampleSize=scale;
		potion.inJustDecodeBounds=false;
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, potion);
		return bitmap;
	}
	/**
	 * 根据高缩放
	 * @param context 暂无
	 * @param resId 暂无
	 * @param desheight 暂无
	 * @return 暂无
	 */
	public static Bitmap compressBitmapWitchHeight(Context context,int resId,int desheight)
	{
		BitmapFactory.Options potion=new BitmapFactory.Options();
		potion.inJustDecodeBounds=true;
		BitmapFactory.decodeResource(context.getResources(),resId,potion);
		int outHeight = potion.outHeight;
		int scale=1;
		if(outHeight>desheight)
		{
			scale=outHeight/desheight;
		}
		potion.inSampleSize=scale;
		potion.inJustDecodeBounds=false;
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, potion);
		return bitmap;
	}
}
