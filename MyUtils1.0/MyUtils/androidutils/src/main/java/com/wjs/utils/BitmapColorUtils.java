package com.wjs.utils;

import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;

public class BitmapColorUtils
{
	/**
	 * 将图片设置成灰色的
	 * @param drawable 图片drawable对象
	 */
	public static Drawable convertGreyImg(Drawable drawable)
	{
		drawable.mutate();//更改图片灰度  
		ColorMatrix cMatrix  = new ColorMatrix();
		cMatrix.setSaturation(0);
		ColorMatrixColorFilter colorFilter  = new ColorMatrixColorFilter(cMatrix);
		drawable.setColorFilter(colorFilter);
		return drawable;
	}
	public static Bitmap convertGreyImg(Bitmap img) {
		int width = img.getWidth();   //获取位图的宽
		int height = img.getHeight();  //获取位图的高

		int []pixels = new int[width * height]; //通过位图的大小创建像素点数组

		img.getPixels(pixels, 0, width, 0, 0, width, height);
		int alpha = 0xFF << 24;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int grey = pixels[width * i + j];

				int red = ((grey  & 0x00FF0000 ) >> 16);
				int green = ((grey & 0x0000FF00) >> 8);
				int blue = (grey & 0x000000FF);

				grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);
				grey = alpha | (grey << 16) | (grey << 8) | grey;
				pixels[width * i + j] = grey;
			}
		}
		Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);
		result.setPixels(pixels, 0, width, 0, 0, width, height);
		return result;
	}
}
