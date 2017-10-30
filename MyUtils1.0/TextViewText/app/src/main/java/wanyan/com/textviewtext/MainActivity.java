package wanyan.com.textviewtext;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Method[] s=this.getClass().getDeclaredMethods();
     /*  mDialog dialog=new mDialog(this,R.style.MyDialog);
        dialog.show();*/

    }
    public void getClass(Class object)
    {
        while(object.getSuperclass()!=null)
        {

        }
    }
}
