package com.wjs.wenyan.myutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.wjs.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJS on 2017/3/15.
 */

public class mView extends View
{
    private int mCalcViewHeight =0;
    private Paint mPaint;
    private int mDrawingheight;
    List<ColorItem> colorItemList=new ArrayList<ColorItem>();
    public mView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public mView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public mView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    public void init(Context context,AttributeSet attrs)
    {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        TypedArray tArray = context.obtainStyledAttributes(attrs,R.styleable.PersonAttr);
        String name = tArray.getString(R.styleable.PersonAttr_colorSet);//获取颜色值和相关高度
        int set=tArray.getInt(R.styleable.PersonAttr_calcType,-1);//选项的下标 0代表px 1代表dp
        int height=tArray.getInt(R.styleable.PersonAttr_drawingheight,-1);
        if(StringUtils.isNotNull(name))
        {
            String[] colorlist=name.split(";");
            for(int i=0;i<colorlist.length;i++)
            {
                String[] coloritem=colorlist[i].split(":");
                if(coloritem.length==2)
                {
                    String colorkey= coloritem[0];
                    String colorheight=coloritem[1];
                    if(colorheight.matches("\\d+"))
                    {
                        int tmpheight = 0;
                        ColorItem item=new ColorItem();
                        if(set==1)
                        {
                            int parseInt=Integer.parseInt(colorheight);

                            tmpheight=(int)((1.0f*parseInt/height)/getResources().getDisplayMetrics().heightPixels);
                        }
                        else if(set==2)
                        {
                            float scale = context.getResources().getDisplayMetrics().density;
                            int dpValue=Integer.parseInt(colorheight);
                            tmpheight= (int) (dpValue * scale + 0.5f);
                        }
                        mCalcViewHeight += tmpheight;
                        item.setHex(colorkey);
                        item.setHeight(tmpheight);
                        colorItemList.add(item);
                    }
                }
            }
        }
        tArray.recycle();


    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        if(widthMode==MeasureSpec.EXACTLY)
        {
            width=widthSize+getPaddingLeft()+getPaddingRight();
        }
        else if(width==MeasureSpec.UNSPECIFIED)
        {
            width=0;
        }
        else
        {
            width=0;
        }
        if(heightMode==MeasureSpec.EXACTLY)
        {
            height=heightSize+getPaddingTop()+getPaddingBottom();
        }
        else if(height==MeasureSpec.UNSPECIFIED)
        {
            height= mCalcViewHeight +getPaddingTop()+getPaddingBottom();
        }
        else
        {
            height=0;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        int height=0;
        for(int i=0;i<colorItemList.size();i++)
        {
            ColorItem mColorItem=colorItemList.get(i);
            String color=mColorItem.getHex();
            mPaint.setColor(Color.parseColor(color));
            canvas.drawRect(0,height,getWidth(),height+=mColorItem.getHeight(),mPaint);
        }

    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        colorItemList.clear();
        colorItemList=null;
    }
    public static class ColorItem
    {
        private String hex;
        private int height;
        public String getHex() {
            return hex;
        }
        public void setHex(String hex) {
            this.hex = hex;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
    }
}
