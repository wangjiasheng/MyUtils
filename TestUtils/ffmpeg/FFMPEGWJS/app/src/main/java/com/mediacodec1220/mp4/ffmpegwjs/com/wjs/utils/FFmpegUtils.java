package com.mediacodec1220.mp4.ffmpegwjs.com.wjs.utils;

import com.mediacodec1220.mp4.ffmpegwjs.DocodeCallback;
import com.mediacodec1220.mp4.ffmpegwjs.MainActivity;

/**
 * Created by WJS on 2016/12/28.
 */

public class FFmpegUtils
{
    public native int FFmpegCommand(int argc,String[] argv);
    public native int convert(String frompath,String desPath,DocodeCallback callback);
}
