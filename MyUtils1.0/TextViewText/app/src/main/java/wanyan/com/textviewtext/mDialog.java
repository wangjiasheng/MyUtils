package wanyan.com.textviewtext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by WJS on 2016/12/15.
 */

public class mDialog extends Dialog
{
    public mDialog(Context context) {
        super(context);
        init();
    }
    public mDialog(Context context,int theme) {
        super(context,theme);
        init();
    }
    public void init()
    {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dd);
    }
}
