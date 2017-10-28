package com.wjs.extendmenu.extendmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wjs.extendmenu.extendmenulib.ExtendMenu;
import com.wjs.extendmenu.extendmenulib.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class ExtendMenuActivity extends AppCompatActivity
{
    ExtendMenu myViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_menu);
        myViewPager= (ExtendMenu) findViewById(R.id.myViewPager);
        List<MainMenu> list=new ArrayList<MainMenu>();
        MainMenu mainMenu1=new MainMenu();
        mainMenu1.setButtonText("菜单");
        mainMenu1.setButtonUrlId(R.mipmap.function1);
        list.add(mainMenu1);

        MainMenu mainMenu2=new MainMenu();
        mainMenu2.setButtonText("文档");
        mainMenu2.setButtonUrlId(R.mipmap.function2);
        list.add(mainMenu2);

        MainMenu mainMenu3=new MainMenu();
        mainMenu3.setButtonText("信息");
        mainMenu3.setButtonUrlId(R.mipmap.function3);
        list.add(mainMenu3);

        MainMenu mainMenu4=new MainMenu();
        mainMenu4.setButtonText("安全");
        mainMenu4.setButtonUrlId(R.mipmap.function4);
        list.add(mainMenu4);
        myViewPager.setData(list);
    }
}
