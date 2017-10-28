package wjs.file.filemanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.myapplication.R;


import org.libsdl.app.SDLActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 家胜 on 2016/4/20.
 */
public class FileManager extends Activity implements AdapterView.OnItemClickListener {
    private Adapter adapter=null;
    private ListView listView=null;
    private String RootDir;
    private String currentDir;
    public static final String URLKEY="Params";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        RootDir=Environment.getExternalStorageDirectory().getAbsolutePath();
        currentDir=RootDir;
        setContentView(R.layout.filemanagerlayout);
        listView= (ListView) findViewById(R.id.mListView);
        listView.setOnItemClickListener(this);
        adapter=new Adapter(Arrays.asList(Environment.getExternalStorageDirectory().listFiles()));
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        File file= (File) parent.getAdapter().getItem(position);
        if(file!=null&&file.isDirectory())
        {
            if(adapter!=null)
            {
                currentDir=file.getAbsolutePath();
                listView.setAdapter(new Adapter(Arrays.asList(file.listFiles())));
                adapter.notifyDataSetChanged();
            }
        }
        else if(file!=null&&file.isFile())
        {
            String path=file.getAbsolutePath();
          if(isVideo(file))
          {
             Intent intent=new Intent(FileManager.this,SDLActivity.class);
             intent.putExtra(URLKEY, path);
             startActivity(intent);
             return;
          }
            if(path.endsWith(".png")
                    || path.endsWith(".jpg")
                    || path.endsWith(".jpeg")
                    || path.endsWith(".gif")){
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "image/*");
                startActivity(intent);
            }else if(path.endsWith(".mp4")
                    || path.endsWith(".3gp")
                    || path.endsWith(".avi")
                    || path.endsWith(".flv")
                    || path.endsWith(".wmv")
                    || path.endsWith(".rmvb")
                    || path.endsWith(".asf")
                    || path.endsWith(".mkv")
                    || path.endsWith(".mpg")){
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "video/*");
                startActivity(intent);
            }else if(path.endsWith(".mp3")
                    || path.endsWith(".ogg")
                    || path.endsWith(".ape")){
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                startActivity(intent);
            }else if(path.endsWith(".apk")){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                startActivity(intent);
            }
            else if(path.endsWith(".c")||path.endsWith(".txt"))
            {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file),"text/plain");
                startActivity(intent);
            }
        }
    }
    class Adapter extends BaseAdapter
    {
        List<File> mList;
        public Adapter(List<File> mList)
        {
            this.mList=mList;
        }
        @Override
        public int getCount()
        {
            return mList==null?0:mList.size();
        }
        @Override
        public Object getItem(int position)
        {
            return mList!=null?mList.get(position):null;
        }
        @Override
        public long getItemId(int position)
        {
            return mList==null?position:0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Holder holder;
            if(convertView==null)
            {
                holder=new Holder();
                convertView= LayoutInflater.from(FileManager.this).inflate(R.layout.filemanagerlistitem,parent,false);
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
            ImageView mFileicon;
            TextView mFileName;
            public void findView(View view)
            {
                mFileicon= (ImageView) view.findViewById(R.id.mImageView);
                mFileName= (TextView) view.findViewById(R.id.filename);
            }
            public void setUp(File str)
            {
                mFileicon.setImageBitmap(getIcon(str));
                mFileName.setText(str.getName());
            }
        }
    }
    public Bitmap getIcon(File fileName)
    {
        if(fileName.isDirectory())
        {
            return BitmapFactory.decodeResource(getResources(),R.drawable.dirphotp);
        }
        else if(fileName.isFile())
        {
            try
            {
                String extname="fileicon/"+getExtName(fileName) + ".png";
                InputStream inputStream=getAssets().open(extname);
                if(inputStream!=null)
                {
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return BitmapFactory.decodeResource(getResources(),R.drawable.file);
            }
        }
        return BitmapFactory.decodeResource(getResources(),R.drawable.file);
    }
    public String getExtName(File file)
    {
        if(file==null)
            return null;
        String fileName=file.getName();
        if(fileName.length()>fileName.lastIndexOf("."))
        {
            String extname=fileName.substring(fileName.lastIndexOf(".")+1);
            return extname;
        }
        return null;
    }
    public boolean nextPage()
    {
        if(currentDir.contains(RootDir)&&!currentDir.equalsIgnoreCase(RootDir))
        {
            File current=new File(currentDir);
            File patrent=current.getParentFile();
            if(patrent!=null&&patrent.isDirectory())
            {
                if(adapter!=null)
                {
                    currentDir=patrent.getAbsolutePath();
                    listView.setAdapter(new Adapter(Arrays.asList(patrent.listFiles())));
                    adapter.notifyDataSetChanged();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isVideo(File parent)
    {
        boolean isVideo=false;
        List<String> list=new ArrayList<String>();
        list.add("mp4");
        list.add("3gp");
        list.add("wmv");
        list.add("rm");
        list.add("rmvb");
        list.add("mkv");
        list.add("mov");
        list.add("flv");
        list.add("m4u");
        list.add("m4v");
        for(int i=0;i<list.size();i++)
        {
            if(getExtName(parent).equals(list.get(i)))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public void onBackPressed()
    {
        if(!nextPage())
        {
            super.onBackPressed();
        }
    }
}
