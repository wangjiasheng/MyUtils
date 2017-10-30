package com.wjs.wenyan.weichat;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils 
{
	public static byte[] drawableToByte(Resources resources,int drawable)
	{
		Bitmap bmp = BitmapFactory.decodeResource(resources, drawable);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
		bmp.recycle();
		return Util.bmpToByteArray(thumbBmp, true);
	}
	public static byte[] fileToByte(String path)
	{
		Bitmap bmp = BitmapFactory.decodeFile(path);
		Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
		bmp.recycle();
		return Util.bmpToByteArray(thumbBmp, true);
	}
}
