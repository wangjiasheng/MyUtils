package com.wenyan.totravelcitywest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 家胜 on 2016/6/6.
 */
public class ShareAdapter extends BaseAdapter
{
    List<ShareBean> mList;
    LayoutInflater mInflater;
    public ShareAdapter(Context context, List<ShareBean> listBean)
    {
        mList=listBean;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }
    @Override
    public Object getItem(int position) {
        return mList==null?null:position;
    }

    @Override
    public long getItemId(int position) {
        return mList==null?0:position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder holder;
        if(convertView==null)
        {
            convertView=mInflater.inflate(R.layout.sharelayout,parent,false);
            holder=new Holder();
            holder.findView(convertView);
            convertView.setTag(holder);
        }
        else

        {
            holder= (Holder) convertView.getTag();
        }
        holder.setUp(mList.get(position));
        return convertView;
    }
    class Holder
    {
        private ImageView mImage;
        private TextView mText;
        public void findView(View view)
        {
            mImage= (ImageView) view.findViewById(R.id.mImage);
            mText= (TextView) view.findViewById(R.id.mText);
        }
        public void setUp(ShareBean bean)
        {
            mImage.setImageResource(bean.getDrableId());
            mText.setText(bean.getTextName());
        }
    }
}
