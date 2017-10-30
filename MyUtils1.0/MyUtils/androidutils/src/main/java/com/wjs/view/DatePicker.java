package com.wjs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.wjs.utils.DayUtils;

import java.util.Calendar;

/**
 * Created by WJS on 2016/11/28.
 * 由于时间问题暂时不支持随着时间自动滚动
 */

public class DatePicker extends LinearLayout
{
    private int yearnum;
    private int monthnum;
    private int daynum;
    private int hournum;
    private int minutenum;

    private int tempyear;
    private int tempmonth;

    private int tempday;
    private int temphour;
    private int tempminute;

    private NumberPicker yearpick;
    private NumberPicker monthpick;
    private NumberPicker daypick;
    private NumberPicker hourpick;
    private NumberPicker minutepick;
    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        initTime();
        addYear();
        addMonth();
        addDate();
        addHour();
        addMinth();
    }
    public void addYear()
    {
        yearpick = new NumberPicker(getContext());
        yearpick.setTag(10001);
        yearpick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        addView(yearpick);
        resetYear();
        yearpick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                String str[]=picker.getDisplayedValues();
                tempyear=Integer.parseInt(str[picker.getValue()].substring(0,str[picker.getValue()].indexOf("年")));
                resetMonth();
                resetDate();
                resetHour();
                resetMinute();
            }
        });
    }
    public void addMonth()
    {
        monthpick = new NumberPicker(getContext());
        monthpick.setTag(10002);
        monthpick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        addView(monthpick);
        resetMonth();
        monthpick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                String str[]=picker.getDisplayedValues();
               tempmonth =Integer.parseInt(str[picker.getValue()].substring(0,str[picker.getValue()].indexOf("月")));
                resetDate();
                resetHour();
                resetMinute();
            }
        });
    }
    public void addDate()
    {
        daypick = new NumberPicker(getContext());
        daypick.setTag(10003);
        daypick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        addView(daypick);
        resetDate();
        daypick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                String str[]=picker.getDisplayedValues();
                tempday=Integer.parseInt(str[picker.getValue()].substring(0,str[picker.getValue()].indexOf("日")));
                resetHour();
                resetMinute();
            }
        });
    }
    public void addHour()
    {
        hourpick = new NumberPicker(getContext());
        hourpick.setTag(10004);
        hourpick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        addView(hourpick);
        resetHour();
        hourpick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                String str[]=picker.getDisplayedValues();
                temphour=Integer.parseInt(str[picker.getValue()].substring(0,str[picker.getValue()].indexOf("时")));
                resetMinute();
            }
        });
    }
    public void addMinth()
    {
        minutepick = new NumberPicker(getContext());
        minutepick.setTag(10005);
        minutepick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        addView(minutepick);
        resetMinute();
        minutepick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                String str[]=picker.getDisplayedValues();
                tempminute=Integer.parseInt(str[picker.getValue()].substring(0,str[picker.getValue()].indexOf("分")));
            }
        });
    }
    private void resetYear()
    {
        int years=10;
        String[] yearstr=new String[years];
        for(int i=0;i<years;i++)
        {
            yearstr[i]=(yearnum+i)+"年";
        }
        yearpick.setMinValue(0);
        yearpick.setMaxValue(0);
        yearpick.setDisplayedValues(yearstr);
        yearpick.setMaxValue(yearstr.length-1);
        yearpick.setMinValue(0);
    }
    private void resetMonth()
    {
        int monthNumber=12;
        if(tempyear>yearnum)
        {
            String[] yearstr=new String[monthNumber];
            for (int i = 1; i <= monthNumber; i++)
            {
                yearstr[i - 1] = i + "月";
            }
            monthpick.setMinValue(0);
            monthpick.setMaxValue(0);
            tempmonth=Integer.parseInt(yearstr[0].substring(0,yearstr[0].indexOf("月")));//滚动年自动重置月份为0 改BUG
            monthpick.setDisplayedValues(yearstr);
            monthpick.setMaxValue(yearstr.length-1);
            monthpick.setMinValue(0);
        }
        else if(tempyear==yearnum)
        {
            String[] yearstr=new String[monthNumber-monthnum+1];
            for (int i = monthnum; i <= monthNumber; i++)
            {
                yearstr[i - monthnum] = i + "月";
            }
            monthpick.setMinValue(0);
            monthpick.setMaxValue(0);
            tempmonth=Integer.parseInt(yearstr[0].substring(0,yearstr[0].indexOf("月")));//滚动年自动重置月份为0 改BUG
            monthpick.setDisplayedValues(yearstr);
            monthpick.setMinValue(0);
            monthpick.setMaxValue(yearstr.length-1);
        }
    }
    public void resetDate()
    {
        int daynumber= DayUtils.getDaysByYearMonth(tempyear,tempmonth);
        if(((tempyear==yearnum)&&(tempmonth>monthnum))||(tempyear>yearnum))
        {
            String dayarras[]=new String[daynumber];
            for(int i=1;i<=daynumber;i++)
            {
                dayarras[i-1]=i+"日";
            }
            daypick.setMinValue(0);
            daypick.setMaxValue(0);
            tempday=Integer.parseInt(dayarras[0].substring(0,dayarras[0].indexOf("日")));//滚动年自动重置月份为0 改BUG
            daypick.setDisplayedValues(dayarras);
            daypick.setMinValue(0);
            daypick.setMaxValue(daynumber-1);
        }
        else if((tempyear==yearnum)&&(tempmonth==monthnum))
        {
            String dayarras[]=new String[daynumber-daynum+1];
            for(int i=daynum;i<=daynumber;i++)
            {
                dayarras[i-daynum]=i+"日";
            }
            daypick.setMinValue(0);
            daypick.setMaxValue(0);
            tempday=Integer.parseInt(dayarras[0].substring(0,dayarras[0].indexOf("日")));//滚动年自动重置月份为0 改BUG
            daypick.setDisplayedValues(dayarras);
            daypick.setMinValue(0);
            daypick.setMaxValue(dayarras.length-1);
        }
    }
    public void resetHour()
    {
        int hour=24;
        if(((tempyear==yearnum)&&(tempmonth==monthnum)&&(tempday>daynum))||((tempyear==yearnum)&&(tempmonth>monthnum))||(tempyear>yearnum))
        {
            String arrays[]=new String[hour];
            for(int i=0;i<hour;i++)
            {
                arrays[i]=i+"时";
            }
            hourpick.setMinValue(0);
            hourpick.setMaxValue(0);
            temphour=Integer.parseInt(arrays[0].substring(0,arrays[0].indexOf("时")));//滚动年自动重置月份为0 改BUG
            hourpick.setDisplayedValues(arrays);
            hourpick.setMinValue(0);
            hourpick.setMaxValue(hour-1);
        }
        else if((tempyear==yearnum)&&(tempmonth==monthnum)&&(tempday==daynum))
        {
            String arrays[]=new String[hour-hournum+1];
            for(int i=hournum;i<=hour;i++)
            {
                arrays[i-hournum]=i+"时";
            }
            hourpick.setMinValue(0);
            hourpick.setMaxValue(0);
            temphour=Integer.parseInt(arrays[0].substring(0,arrays[0].indexOf("时")));//滚动年自动重置月份为0 改BUG
            hourpick.setDisplayedValues(arrays);
            hourpick.setMinValue(0);
            hourpick.setMaxValue(arrays.length-1);
        }
    }
    public void resetMinute()
    {
        int minute=60;
        if(((tempyear==yearnum)&&(tempmonth==monthnum)&&(tempday==daynum)&&(temphour>hournum))||((tempyear==yearnum)&&(tempmonth==monthnum)&&(tempday>daynum))||((tempyear==yearnum)&&(tempmonth>monthnum))||(tempyear>yearnum))
        {
            String arrays[]=new String[minute];
            for(int i=0;i<minute;i++)
            {
                arrays[i]=i+"分";
            }
            minutepick.setMinValue(0);
            minutepick.setMaxValue(0);
            tempminute=Integer.parseInt(arrays[0].substring(0,arrays[0].indexOf("分")));//滚动年自动重置月份为0 改BUG
            minutepick.setDisplayedValues(arrays);
            minutepick.setMinValue(0);
            minutepick.setMaxValue(minute-1);
        }
        else if((tempyear==yearnum)&&(tempmonth==monthnum)&&(tempday==daynum)&&(temphour==hournum))
        {
            String arrays[]=new String[minute-minutenum+1];
            for(int i=minutenum;i<=minute;i++)
            {
                arrays[i-minutenum]=i+"分";
            }
            minutepick.setMinValue(0);
            minutepick.setMaxValue(0);
            tempminute=Integer.parseInt(arrays[0].substring(0,arrays[0].indexOf("分")));//滚动年自动重置月份为0 改BUG
            minutepick.setDisplayedValues(arrays);
            minutepick.setMinValue(0);
            minutepick.setMaxValue(arrays.length-1);
        }
    }
    public void initTime()
    {
        Calendar calendar=Calendar.getInstance();
        tempyear=yearnum=calendar.get(Calendar.YEAR);
        tempmonth=monthnum=calendar.get(Calendar.MONTH)+1;
        tempday=daynum=calendar.get(Calendar.DAY_OF_MONTH);
        temphour=hournum=calendar.get(Calendar.HOUR_OF_DAY);
        tempminute=minutenum=calendar.get(Calendar.MINUTE);
    }
    public void setCurrentTime(int year,int month,int day,int hour,int minute)
    {
        tempyear=yearnum=year;
        tempmonth=monthnum=month;
        tempday=daynum=day;
        temphour=hournum=hour;
        tempminute=minutenum=minute;
        resetYear();
        resetMonth();
        resetDate();
        resetHour();
        resetMinute();
    }
    public void setCurrentTime()
    {
        Calendar calendar=Calendar.getInstance();
        yearnum=calendar.get(Calendar.YEAR);
        monthnum=calendar.get(Calendar.MONTH)+1;
        daynum=calendar.get(Calendar.DAY_OF_MONTH);
        hournum=calendar.get(Calendar.HOUR_OF_DAY);
        minutenum=calendar.get(Calendar.MINUTE);
    }
    public int getTempyear() {
        return tempyear;
    }

    public int getTempmonth() {
        return tempmonth;
    }

    public int getTempday() {
        return tempday;
    }

    public int getTemphour() {
        return temphour;
    }

    public int getTempminute() {
        return tempminute;
    }
}
