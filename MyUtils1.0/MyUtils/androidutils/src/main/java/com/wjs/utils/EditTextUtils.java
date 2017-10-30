package com.wjs.utils;

import android.text.Editable;
import android.text.Selection;
import android.widget.EditText;

public class EditTextUtils
{
	/**
	 * 将EditText光标移动到最后
	 * @param edit 暂无
	 */
	public static void moveToEnd(EditText edit)
	{
		Editable etext = edit.getText();
		int position = etext.length();
		Selection.setSelection(etext, position);
	}
}
