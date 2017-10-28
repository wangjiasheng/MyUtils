package wanyan.com.newtooglebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by WJS on 2017/2/14.
 */

public abstract class mButton extends LinearLayout {
    public mButton(Context context) {
        super(context);
    }
    public mButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public mButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public abstract void isSelect(boolean isselect);
    public abstract void isCheck(boolean ischeck);
}
