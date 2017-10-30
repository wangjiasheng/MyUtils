package wanyan.com.musicplayer;

import java.io.IOException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class LocalMusicBindServices extends Service implements OnPreparedListener, OnCompletionListener, OnBufferingUpdateListener, OnSeekCompleteListener
{
	private MyBinder bindler=null;
	private MediaPlayer player=null;
	private boolean showLog=false;
	private PlayerStatus status=PlayerStatus.STOP;
	private StateChangeListener mStateChangeListener=null;
	private ProgressChangeListener mProgressChangeListener=null;
	private BufferChangeListener mBufferChangeListener=null;
	private final static int PROGRESS=1000;
	private final static int REPROGREDD=1001;
	/**
	 * @author wjs
	 *
	 */
	public enum PlayerStatus
	{
		PLAY,STOP,PAUSE,END
	}
	public interface MusicListener extends StateChangeListener,ProgressChangeListener,BufferChangeListener
	{

	}
	public interface StateChangeListener
	{
		public PlayerStatus playerState(PlayerStatus status);
	}

	/**
	 * 进度条回调接口
	 */
	public interface ProgressChangeListener
	{
		public void publishProgress(float progress);
	}

	/**
	 * 缓存进度回调接口
	 */
	public interface BufferChangeListener
	{
		public void bufferchange(int progress);
	}
	Handler handler=new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			switch(msg.what)
			{
				case PROGRESS:
					handler.sendEmptyMessageDelayed(PROGRESS, 500);
					if(mProgressChangeListener!=null)
					{
						if(player!=null&&status==PlayerStatus.PLAY||status==PlayerStatus.PAUSE)
						{
							float progress=(1.0f*player.getCurrentPosition()/player.getDuration())*100;
							mProgressChangeListener.publishProgress(progress);
						}
						else if(status==PlayerStatus.STOP)
						{
							mProgressChangeListener.publishProgress(0);
							mBufferChangeListener.bufferchange(0);
						}
					}
					break;
			}
		};
	};
	public void init()
	{
		if(bindler==null)
		{
			bindler=new MyBinder();
		}
		initMediaplayer();
	}
	@Override
	public void onCreate() 
	{
		if(showLog)
		Log.i("WJS", "onCreate");
		super.onCreate();
		init();
		handler.sendEmptyMessage(PROGRESS);
	}
	@Override
	public IBinder onBind(Intent intent) 
	{
		if(showLog)
		Log.i("WJS", "onBind");
		return bindler;
	}
	@Override
	public boolean onUnbind(Intent intent) 
	{
		if(showLog)
		Log.i("WJS", "onUnbind");
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() 
	{
		if(showLog)
		Log.i("WJS", "onDestroy");
		bindler.stop();
		handler.sendEmptyMessage(REPROGREDD);
		super.onDestroy();
	}
	public void initMediaplayer()
	{
		if(player==null)
		{
			player=new MediaPlayer();
			player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			player.setOnPreparedListener(LocalMusicBindServices.this);
			player.setOnCompletionListener(LocalMusicBindServices.this);
			player.setOnBufferingUpdateListener(LocalMusicBindServices.this);
			player.setOnSeekCompleteListener(LocalMusicBindServices.this);
		}
	}
	public class MyBinder extends Binder implements PlayerContral
	{
		/**
		 * @param context 上下文对象
		 * @param uri 需要播放的音乐文件的URI
		 */
		@Override
		public void play(Context context,Uri uri) 
		{
			initMediaplayer();
			if(status==PlayerStatus.PLAY||status==PlayerStatus.PAUSE||status==PlayerStatus.END)
			{
				player.stop();
				player.reset();
				status=PlayerStatus.STOP;
				if(mStateChangeListener!=null)
				{
					mStateChangeListener.playerState(status);
				}
			}
			try 
			{
				mProgressChangeListener.publishProgress(0);
				mBufferChangeListener.bufferchange(0);
				player.setDataSource(context, uri);
				player.prepareAsync();
			}
			catch (IllegalArgumentException e) 
			{
				e.printStackTrace();
			}
			catch (SecurityException e) 
			{
				e.printStackTrace();
			} 
			catch (IllegalStateException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

		/**
		 * 停止播放
		 */
		@Override
		public void stop() 
		{
			if(status!=PlayerStatus.STOP&&player!=null)
			{
				player.stop();
				player.reset();
				status=PlayerStatus.STOP;
				if(mStateChangeListener!=null)
				{
					mStateChangeListener.playerState(status);
				}
			}
		}

		/**
		 * 暂停播放
		 */
		@Override
		public void pause()
		{
			if(status==PlayerStatus.PLAY&&player!=null)
			{
				player.pause();
				status=PlayerStatus.PAUSE;
				if(mStateChangeListener!=null)
				{
					mStateChangeListener.playerState(status);
				}
			}
		}

		/**
		 * 获取播放状态
		 */
		public void checkStatus()
		{
			if(mStateChangeListener!=null)
			{
				mStateChangeListener.playerState(status);
			}
		}

		/**
		 * 继续播放
		 */
		@Override
		public void resume() 
		{
			if(player!=null&&(status==PlayerStatus.PAUSE||status==PlayerStatus.END))
			{
				player.start();
				status=PlayerStatus.PLAY;
				if(mStateChangeListener!=null)
				{
					mStateChangeListener.playerState(status);
				}
			}
		}

		/**
		 * @param msec 跳转的时间
		 */
		public void seek(int msec)
		{
			if(status==PlayerStatus.PLAY||status==PlayerStatus.PAUSE)
			{
				player.seekTo(msec);
			}
			if(status==PlayerStatus.END)
			{
				if(!player.isPlaying())
				{
					resume();
				}
			}
		}

		/**
		 * @return 音乐的长度
		 */
		public int getDuration()
		{
			if(status!=PlayerStatus.STOP)
			return player.getDuration();
			return 0;
		}

		/**
		 * @return 播放状态
		 */
		public PlayerStatus getStatus()
		{
			return status;
		}

		/**
		 * 释放播放器资源
		 */
		public void release()
		{
			if(status==PlayerStatus.PLAY||status==PlayerStatus.PAUSE)
			{
				player.stop();
				player.reset();
				player.release();
				player.stop();
				status=PlayerStatus.STOP;
				if(mStateChangeListener!=null)
				{
					mStateChangeListener.playerState(status);
				}
			}
		}

		/**
		 * @param stateChangeListener 播放器状态监听器
		 */
		@Override
		public void setOnStateChangeListener(StateChangeListener stateChangeListener) 
		{
 			LocalMusicBindServices.this.mStateChangeListener=stateChangeListener;
		}

		/**
		 * @param progressChangeListener 播放器进度监听
		 */
		public void setOnProgressChangeListener(ProgressChangeListener progressChangeListener)
		{
			LocalMusicBindServices.this.mProgressChangeListener=progressChangeListener;
		}

		/**
		 * @return 获取当前的播放进度
		 */
		@Override
		public long getCurrentPosition()
		{
			if(status!=PlayerStatus.STOP)
				return player.getCurrentPosition();
				return 0;
		}

		/**
		 * @param mBufferChangeListener 缓存进度监听
		 */
		@Override
		public void setonBufferChangeListener(BufferChangeListener mBufferChangeListener) {
			LocalMusicBindServices.this.mBufferChangeListener=mBufferChangeListener;
		}
	}
	interface PlayerContral
	{
		public void play(Context context, Uri url);
		public void stop();
		public void pause();
		public void resume();
		public long getCurrentPosition();
		public void setOnStateChangeListener(StateChangeListener stateChangeListener);
		public void setonBufferChangeListener(BufferChangeListener progress);
	}
	@Override
	public void onPrepared(MediaPlayer mp) 
	{
		mp.start();
		status=PlayerStatus.PLAY;
		if(mStateChangeListener!=null)
		{
			mStateChangeListener.playerState(status);
		}
	}
	@Override
	public void onCompletion(MediaPlayer mp) 
	{
		status=PlayerStatus.END;
		if(mStateChangeListener!=null)
		{
			mStateChangeListener.playerState(status);
		}
	}
	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent)
	{
		if(mBufferChangeListener!=null)
		{
			if(status==PlayerStatus.STOP)
			{
				mBufferChangeListener.bufferchange(0);
			}
			else
			{
				mBufferChangeListener.bufferchange(percent);
			}
		}
	}
	@Override
	public void onSeekComplete(MediaPlayer mp) 
	{
	}
}
