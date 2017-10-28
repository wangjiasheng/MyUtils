package wjs.player.control;

/**
 * Created by 家胜 on 2016/4/26.
 */
public class MediaController
{
    public native void init();
    public native void play(String url);
    public native void pause();
    public native void resume();
    public native void seek(long position);
    public native long getCurrentPosition();
    public native long getDuration();
    public native void fastForward(int time);
    public native void fastRewind(int time);
    public native void release();
    public void progress(long progress,long duration)
    {

    }
}
