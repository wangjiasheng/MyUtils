package com.mediacodec1220.mp4.ffmpegwjs;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mediacodec1220.mp4.ffmpegwjs.com.wjs.utils.FFmpegUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    static
    {
        System.loadLibrary("avutil-55");
        System.loadLibrary("swresample-2");
        System.loadLibrary("avcodec-57");
        System.loadLibrary("avformat-57");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("avdevice-57");
        System.loadLibrary("main");
    }
    TextView mtext;
    String inputPath;
    String outPath;
    String command;
    FFmpegUtils utils=new FFmpegUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtext= (TextView) findViewById(R.id.mtext);
        mtext.setText("开始转码");
       inputPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/1.mov";
       outPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/1.mp4";

        command="ffmpeg -i "+inputPath+" -vcodec h264 -s 1920*1080 -an -f mp4 "+outPath;//编码mpeg4封装格式  m4v   成功

        final   String cmd2="ffmpeg -i "+inputPath+" -vcodec copy -an "+outPath;//视频分离成功
        final   String cmd3="ffmpeg -i "+inputPath+" -vcodec copy -vn "+outPath;//
        final String com6="ffmpeg -i "+Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/4012.mp3"+" -acodec wmav2 -ab 64k -ar 44100 "+Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/4013.wav";//音频转码 wmav2


        final String com7="ffmpeg -codecs";//-vcodec   pc
        final String com8="ffmpeg -formats";//-f   pc
        final String com9="ffmpeg -filters";//vf args   pc
        findViewById(R.id.encoder).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        new Thread(){
            @Override
            public void run() {
                //   super.run(
                if(new File(inputPath).exists())
                {
                    new File(outPath).delete();
                    String[] argv=command.split(" ");
                    Integer argc=argv.length;
                    int iii=utils.FFmpegCommand(argc,argv);
                    Log.e("LXS_Test","java_"+iii);
                    if(iii==-11000)
                    {
                        mtext.setText("修复文件已经存在");


                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtext.setText("sucess");
                        }
                    });
                }
                else
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mtext.setText("文件不存在"+inputPath);
                        }
                    });
                }

            }
        }.start();
    }
}
