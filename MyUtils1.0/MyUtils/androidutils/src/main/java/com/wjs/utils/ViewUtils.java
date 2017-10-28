package com.wjs.utils;

import android.graphics.Bitmap;
import android.view.View;

public class ViewUtils
{
    public static Bitmap loadBitmapFromView(View v) {
        ViewDimension calcView = calcView(v);
        return getViewBitmap(v, calcView.getWidth(), calcView.getHeight());
    }
    private static Bitmap getViewBitmap(View comBitmap, int width, int height) {
        Bitmap bitmap = null;
        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);
            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);
            // Reset the drawing cache background color to fully transparent  
            // for the duration of this operation  
            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, width, height);

            comBitmap.buildDrawingCache();
            Bitmap cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view  
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }
        return bitmap;
    }
    public static ViewDimension calcView(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        ViewDimension viewdimen=new ViewDimension(view.getMeasuredWidth(),view.getMeasuredHeight());
        return viewdimen;
    }
}
class ViewDimension
{
    public ViewDimension(int width, int height)
    {
        super();
        this.width = width;
        this.height = height;
    }
    private int width=0;
    private int height=0;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
