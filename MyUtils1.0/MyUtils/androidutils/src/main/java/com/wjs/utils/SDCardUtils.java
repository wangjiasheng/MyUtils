package com.wjs.utils;
import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/**
 * SD卡相关的辅助类 
 *
 *
 *
 */
public class SDCardUtils
{
    private SDCardUtils()
    {  
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用 
     *
     * @return 判断SDCard是否可用
     */
    public static boolean isSDCardMounted()
    {
        String string = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equalsIgnoreCase(string))
        {
            return true;
        }
        return false;
    }

    /**
     * 获取SD卡路径 
     *
     * @return 获取SD卡路径
     */
    public static String getSDCardPath()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte 
     *
     * @return  获取SD卡的剩余容量 单位byte
     */
    public static long getSDCardAllSize()
    {
        if (isSDCardMounted())
        {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量  
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）  
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte 
     *
     * @param filePath 获取指定路径所在空间的剩余可用容量字节数，单位byte
     * @return 容量字节 SDCard可用空间，内部存储可用空间 
     */
    public static long getFreeBytes(String filePath)
    {
        // 如果是sd卡的下的路径，则获取sd卡可用容量  
        if (filePath.startsWith(getSDCardPath()))
        {
            filePath = getSDCardPath();
        } else
        {// 如果是内部存储的路径，则获取内存存储的可用容量  
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径 
     *
     * @return 获取系统存储路径
     */
    public static String getRootDirectoryPath()
    {
        return Environment.getRootDirectory().getAbsolutePath();
    }
    public static boolean createDir(String path)
    {
        File file=new File(path);
        if(!file.exists())
        {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * @param context 上下文对象
     *  @param dir 可以为null
     * @return path /storage/emulated/0/Android/data/com.wjs.loadphoto.demo/files
     */
    public static final String getExternalAndroidDir(Context context, String dir)
    {
        String path=context.getExternalFilesDir(dir).getAbsolutePath();
        return path;
    }

    /**
     * @param context 上下文对象
     * @return path /data/data/com.wjs.loadphoto.demo/files
     */
    public static final String getAndroidDir(Context context)
    {
        return context.getFilesDir().getAbsolutePath();
    }
    /**
     * @param context 上下文对象
     * @return path /storage/emulated/0/Android/data/com.wjs.loadphoto.demo/cache
     */
    public static final String getExternalCacheDir(Context context)
    {
        return context.getExternalCacheDir().getAbsolutePath();
    }
    /**
     * @param context 上下文对象
     * @return path /data/data/com.wjs.loadphoto.demo/cache
     */
    public static final String getCacheDir(Context context)
    {
        return context.getCacheDir().getAbsolutePath();
    }
}  