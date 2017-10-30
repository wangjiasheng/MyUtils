package wanyan.com.newtooglebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by WJS on 2017/2/14.
 */

public class newToogleButton extends ViewGroup
{
    private int selectItem=-1;
    private boolean isoneselect=false;
    private boolean isscendselect=false;
    private boolean isthridselect=false;
    private boolean isfourselect=false;
    public newToogleButton(Context context) {
        super(context);
    }
    public newToogleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public newToogleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 最终控件得宽和高
         */
        int width = 0;
        int height = 0;
        /**
         * 临时最大的宽和高
         */
        int tWidth = 0;
        int tHeight = 0;

        int cCount = getChildCount();
        MarginLayoutParams cParams = null;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            int cWidth = childView.getMeasuredWidth();
            int cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            if(cWidth>tWidth)
            {
                tWidth=cWidth;
            }
            if(cHeight>tHeight)
            {
                tHeight=cHeight;
            }
        }
        width = cCount*tWidth;
        height =cCount*tHeight;
        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        //setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight: height);
        setMeasuredDimension(sizeWidth, tHeight);
    }
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) //决定该ViewGroup的LayoutParams
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            int cl = 0, ct = 0, cr = 0, cb = 0;
            cl=(getWidth()/4)*i;
            ct = cParams.topMargin;
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getX()>getWidth()/4*3)
        {
            if(selectItem==3)
            {
                isfourselect=!isfourselect;
                mButton childView = (mButton) getChildAt(3);
                if(!isfourselect) {
                    childView.isSelect(true);
                }
                else
                {
                    childView.isSelect(false);
                }
            }
            else
            {
                selectItem=3;
                int cCount = getChildCount();
                for (int i = 0; i < cCount; i++)
                {
                    mButton childView = (mButton) getChildAt(i);
                    if(i==selectItem)
                    {
                        childView.isCheck(true);
                    }
                    else
                    {
                        childView.isCheck(false);
                    }
                }
            }
        }
        else if(event.getX()>getWidth()/2)
        {
           if(selectItem==2)
           {
                isthridselect=!isthridselect;
               mButton childView = (mButton) getChildAt(2);
               if(!isthridselect) {
                   childView.isSelect(true);
               }
               else
               {
                   childView.isSelect(false);
               }
           }
           else
           {
                selectItem=2;
               int cCount = getChildCount();
               for (int i = 0; i < cCount; i++)
               {
                   mButton childView = (mButton) getChildAt(i);
                   if(i==selectItem)
                   {
                       childView.isCheck(true);
                   }
                   else
                   {
                       childView.isCheck(false);
                   }
               }
           }
        }
        else if(event.getX()>getWidth()/4)
        {
            if(selectItem==1)
            {
                isscendselect=!isscendselect;
                mButton childView = (mButton) getChildAt(1);
                if(!isscendselect) {
                    childView.isSelect(true);
                }
                else
                {
                    childView.isSelect(false);
                }
            }
            else
            {
                selectItem=1;
                int cCount = getChildCount();
                for (int i = 0; i < cCount; i++)
                {
                    mButton childView = (mButton) getChildAt(i);
                    if(i==selectItem)
                    {
                        childView.isCheck(true);
                    }
                    else
                    {
                        childView.isCheck(false);
                    }
                }
            }
        }
        else
        {
            if(selectItem==0)
            {
                isoneselect=!isoneselect;
                mButton childView = (mButton) getChildAt(0);
                if(!isoneselect) {
                    childView.isSelect(true);
                }
                else
                {
                    childView.isSelect(false);
                }
            }
            else
            {
                selectItem=0;
                int cCount = getChildCount();
                for (int i = 0; i < cCount; i++)
                {
                    mButton childView = (mButton) getChildAt(i);
                    if(i==selectItem)
                    {
                        childView.isCheck(true);
                    }
                    else
                    {
                        childView.isCheck(false);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
