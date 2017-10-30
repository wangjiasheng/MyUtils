package wanyan.com.networklib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WJS on 2016/9/23.
 */

public class Adapter extends BaseAdapter
{
    private final Context context;
    private final List<DownInfo> bean;

    public Adapter(Context context, List<DownInfo> bean)
    {
        this.context=context;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder holder;
        if(convertView==null)
        {
            holder=new Holder();
            convertView= LayoutInflater.from(context).inflate(R.layout.downitem,parent,false);
            holder.findView(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder= (Holder) convertView.getTag();
        }
        holder.setUp(bean.get(position));
        return convertView;
    }
    class Holder
    {
        private TextView video_title;
        private Button video_down;
        public void findView(View view)
        {
            video_title= (TextView) view.findViewById(R.id.video_title);
            video_down= (Button) view.findViewById(R.id.video_down);
        }

        public void setUp(DownInfo up) {
            video_title.setText(up.getName());
        }
    }
}
