package wanyan.com.newtooglebutton;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by WJS on 2017/2/14.
 */

public class mButtonImpl extends mButton {
    public mButtonImpl(Context context) {
        super(context);
    }
    public mButtonImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public mButtonImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void isSelect(boolean isselect) {
       if(isselect)
       {
           ((ImageView) ((LinearLayout)getChildAt(1)).getChildAt(0)).setImageResource(R.mipmap.qian_top);
           ((ImageView) ((LinearLayout)getChildAt(1)).getChildAt(1)).setImageResource(R.mipmap.sheng_bottom);
       }
        else
       {
           ((ImageView) ((LinearLayout)getChildAt(1)).getChildAt(0)).setImageResource(R.mipmap.sheng_to);
           ((ImageView) ((LinearLayout)getChildAt(1)).getChildAt(1)).setImageResource(R.mipmap.qian_bottom);
       }
    }
    @Override
    public void isCheck(boolean ischeck) {
        if(ischeck)
        {
            setBackgroundColor(Color.CYAN);
        }
        else
        {
            setBackgroundColor(Color.WHITE);
        }
    }
}
