package com.wjs.extendmenu.extendmenulib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 家胜 on 2015/11/11.
 */
public class MainMunuAdapter extends BaseAdapter
{
    private Context context;
    List<MainMenu> mList=null;
    LayoutInflater mInflater=null;
    public MainMunuAdapter(Context context, List<MainMenu> mList)
    {
        mInflater=LayoutInflater.from(context);
        this.mList=mList;
        this.context=context;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HolderView mHolderView=null;
        if(convertView==null)
        {
            mHolderView=new HolderView();
            convertView = mInflater.inflate(R.layout.mainmenu_layout, parent, false);
            convertView.setTag(mHolderView);
            mHolderView.findView(convertView);
        }
        else
        {
            mHolderView=(HolderView)convertView.getTag();
        }
        //mHolderView.clear();
        mHolderView.setUp(mList.get(position));
        Animation anim=AnimationUtils.loadAnimation(convertView.getContext(),R.anim.scaleanimate);
        anim.setDuration((int)((Math.random()*100)));
        convertView.startAnimation(anim);
        return convertView;
    }
    class HolderView
    {
        private ImageView mImageView=null;
        private TextView mTextView=null;
        public void clear()
        {
            mImageView.setImageBitmap(null);
            mTextView.setText("");
        }
        public void findView(View view)
        {
            mImageView=(ImageView)view.findViewById(R.id.mImageView);
            mTextView=(TextView)view.findViewById(R.id.mText);
        }
        public void setUp(MainMenu menu)
        {
            mTextView.setText(menu.getButtonText());
            if(menu.getButtonUrlId()!=0)
            {
                mImageView.setImageResource(menu.getButtonUrlId());
            }
        }
    }
}
