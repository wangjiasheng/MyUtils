package com.wjs.view.lib;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChildViewPager extends ViewPager
{
    /** 触摸时按下的点 **/
    private PointF downP = new PointF();
    /** 触摸时当前的点 **/
    private PointF curP = new PointF();
    private OnSingleTouchListener onSingleTouchListener;
    private EdgeEffectCompat leftEdge;
    private EdgeEffectCompat rightEdge;
    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public ChildViewPager(Context context) {
        super(context);
       setUp();
    }
    public void setUp()
    {
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub  
        //每次进行onTouch事件都记录当前的按下的坐标  
        curP.x = arg0.getX();
        curP.y = arg0.getY();

        if(arg0.getAction() == MotionEvent.ACTION_DOWN){
            //记录按下时候的坐标  
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变  
            downP.x = arg0.getX();
            downP.y = arg0.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(arg0.getAction() == MotionEvent.ACTION_MOVE){
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰  
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(arg0.getAction() == MotionEvent.ACTION_UP){
            //在up时判断是否按下和松手的坐标为一个点  
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick  
            if(downP.x==curP.x && downP.y==curP.y){
                onSingleTouch(getCurrentItem());
                return true;
            }
        }

        return super.onTouchEvent(arg0);
    }

    /**
     * 单击 
     */
    public void onSingleTouch(int currentposition) {
        if (onSingleTouchListener!= null) {

            onSingleTouchListener.onSingleTouch(currentposition);
        }
    }

    /**
     * 创建点击事件接口 
     * @author wanpg
     *
     */
    public interface OnSingleTouchListener {
        public void onSingleTouch(int currentposition);
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }
}