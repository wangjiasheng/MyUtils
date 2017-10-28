package com.wjs.view;

import com.wjs.utils.DensityUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ProgressWheel extends View {
	private int progress = 0;
	private int offset = DensityUtils.dp2px(getContext(), 5);
	private int paintwitdh = DensityUtils.dp2px(getContext(), 1);

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
		invalidate();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPaintwitdh() {
		return paintwitdh;
	}

	public void setPaintwitdh(int paintwitdh) {
		this.paintwitdh = paintwitdh;
	}

	public ProgressWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(DensityUtils.dp2px(getContext(), 1));
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.RED);
		RectF rect = new RectF();
		rect.left = DensityUtils.dp2px(getContext(),1);
		rect.right = this.getWidth()-DensityUtils.dp2px(getContext(),1);
		rect.top = DensityUtils.dp2px(getContext(),1);
		rect.bottom = this.getHeight()-DensityUtils.dp2px(getContext(),1);
		int jiaodu = (progress * 360) / 100;
		canvas.drawArc(rect, -90, jiaodu, false, paint);
		Paint paint2 = new Paint();
		paint2.setColor(Color.RED);
		Paint paint3 = new Paint();
		paint3.setColor(Color.RED);
		paint3.setStrokeWidth(paintwitdh);
		paint3.setAntiAlias(true);
		if(progress==0)
		{
			canvas.drawColor(Color.TRANSPARENT);
		}
		else if (progress>0&&progress<100) 
		{
			canvas.drawRect(offset, offset, this.getWidth() - offset,
					this.getHeight() - offset, paint2);
		} else {
			canvas.drawLine(
					this.getWidth() / 2 - DensityUtils.dp2px(getContext(), 3),
					this.getHeight() / 2, this.getWidth() / 2, this.getHeight()
							/ 2 + DensityUtils.dp2px(getContext(), 3), paint3);
			canvas.drawLine(this.getWidth() / 2, this.getHeight() / 2
					+ DensityUtils.dp2px(getContext(), 3), this.getWidth() / 2
					+ DensityUtils.dp2px(getContext(), 4), this.getHeight() / 2
					- DensityUtils.dp2px(getContext(), 2), paint3);
		}
	}
}
