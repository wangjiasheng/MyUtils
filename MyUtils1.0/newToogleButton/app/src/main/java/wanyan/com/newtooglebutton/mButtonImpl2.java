package wanyan.com.newtooglebutton;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by WJS on 2017/2/14.
 */

public class mButtonImpl2 extends mButton{
    public mButtonImpl2(Context context) {
        super(context);
    }
    public mButtonImpl2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public mButtonImpl2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void isSelect(boolean isselect) {

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
