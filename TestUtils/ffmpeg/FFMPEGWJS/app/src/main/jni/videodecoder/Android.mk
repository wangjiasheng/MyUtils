LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := main
LOCAL_SRC_FILES := io_github_yanbober_ndkapplication_NdkJniUtils.c ffmpeg.c ffmpeg_opt.c ffmpeg_filter.c cmdutils.c decodec.c

#depends
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../include/

LOCAL_SHARED_LIBRARIES := avcodec-57-prebuilt avdevice-57-prebuilt avfilter-6-prebuilt avformat-57-prebuilt avutil-55-prebuilt x264-prebuilt avswresample-2-prebuilt swscale-4-prebuilt postproc-54-prebuilt

LOCAL_LDLIBS += -lGLESv1_CM -lGLESv2 -llog -ljnigraphics -lz -landroid -lm -pthread

#finally
include $(BUILD_SHARED_LIBRARY)
