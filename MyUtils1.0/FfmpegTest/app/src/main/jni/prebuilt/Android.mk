LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
   LOCAL_MODULE :=  avutil-55-prebuilt
   LOCAL_SRC_FILES := libavutil-55.so
   include $(PREBUILT_SHARED_LIBRARY)  
     
   include $(CLEAR_VARS)  
   LOCAL_MODULE :=  avswresample-2-prebuilt
   LOCAL_SRC_FILES := libswresample-2.so
   include $(PREBUILT_SHARED_LIBRARY)  
     
   include $(CLEAR_VARS)  
   LOCAL_MODULE :=  swscale-4-prebuilt
   LOCAL_SRC_FILES := libswscale-4.so
   include $(PREBUILT_SHARED_LIBRARY)  
     
   include $(CLEAR_VARS)  
   LOCAL_MODULE := avcodec-57-prebuilt
   LOCAL_SRC_FILES := libavcodec-57.so
   include $(PREBUILT_SHARED_LIBRARY)  
     
   include $(CLEAR_VARS)  
   LOCAL_MODULE := avdevice-57-prebuilt
   LOCAL_SRC_FILES := libavdevice-57.so
   include $(PREBUILT_SHARED_LIBRARY)      

   include $(CLEAR_VARS)  
   LOCAL_MODULE := avformat-57-prebuilt
   LOCAL_SRC_FILES := libavformat-57.so
   include $(PREBUILT_SHARED_LIBRARY)  
     
   include $(CLEAR_VARS)  
   LOCAL_MODULE := avfilter-6-prebuilt
   LOCAL_SRC_FILES := libavfilter-6.so
   include $(PREBUILT_SHARED_LIBRARY)

    include $(CLEAR_VARS)
    LOCAL_MODULE := SDL2-prebuilt
    LOCAL_SRC_FILES := libSDL2.so
    include $(PREBUILT_SHARED_LIBRARY)
