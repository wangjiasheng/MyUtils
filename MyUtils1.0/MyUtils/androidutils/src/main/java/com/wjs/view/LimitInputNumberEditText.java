package com.wjs.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LimitInputNumberEditText extends RelativeLayout implements TextWatcher
{
	/**
	 * ��ʾ����
	 */
	private TextView mPromptText=null;
	/**
	 * ʣ������
	 */
	private TextView mSurplusText=null;
	/**
	 * �����
	 */
	private EditText mContentEditText=null;
	/**
	 * ���ֵ���󳤶�
	 */
	private int maxLen = 300;  
	private int currentLen=0;
	public LimitInputNumberEditText(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}
	public LimitInputNumberEditText(Context context) 
	{
		super(context);
		init(context);
	}
	public LimitInputNumberEditText(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init(context);
	}
	public void init(Context context)
	{
		addView(context);
		addListener();
	}
	public void addView(Context context)
	{
		mPromptText=new TextView(context);
		mPromptText.setText("�������"+maxLen+"��");
		mPromptText.setTextSize(12);
		mPromptText.setTextColor(Color.rgb(124, 124, 124));
		mPromptText.setId(1000);
		addView(mPromptText);
		
		LayoutParams tvlayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		tvlayoutParams.topMargin=10;
		mPromptText.setLayoutParams(tvlayoutParams);
		
		mSurplusText=new TextView(context);
		mSurplusText.setTextSize(15);
		mSurplusText.setText(currentLen+"/"+maxLen);
		addView(mSurplusText);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mSurplusText.setLayoutParams(layoutParams);
		
		mContentEditText=new EditText(context);
		mContentEditText.setTextSize(17);
		mContentEditText.setTextColor(Color.rgb(94, 94, 94));
		mContentEditText.setBackgroundDrawable(null);
		addView(mContentEditText);
		
		LayoutParams editTextlayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		editTextlayoutParams.addRule(RelativeLayout.BELOW,1000);
		editTextlayoutParams.setMargins(0, 15, 0, 0);
		mContentEditText.setGravity(Gravity.TOP);
		mContentEditText.setPadding(0, 0, 0, 0);
		mContentEditText.setLayoutParams(editTextlayoutParams);
	}
	public void addListener()
	{
		mContentEditText.addTextChangedListener(this);
	}
	@Override
	public void afterTextChanged(Editable arg0) 
	{
	}
	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
	{
		
	}
	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) 
	{
		Editable editable = mContentEditText.getText();  
		currentLen = editable.length();  
        if(currentLen > maxLen)  
        {  
            int selEndIndex = Selection.getSelectionEnd(editable);  
            String str = editable.toString();  
            //��ȡ���ַ���  
            String newStr = str.substring(0,maxLen);  
            mContentEditText.setText(newStr);  
            editable = mContentEditText.getText();  
              
            //���ַ����ĳ���  
            currentLen = editable.length();  
            //�ɹ��λ�ó����ַ�������  
            if(selEndIndex > currentLen)  
            {  
                selEndIndex = editable.length();  
            }  
            //�����¹�����ڵ�λ��  
            Selection.setSelection(editable, selEndIndex);  
        }
        mSurplusText.setText(currentLen + "/" + maxLen);
	}
}
