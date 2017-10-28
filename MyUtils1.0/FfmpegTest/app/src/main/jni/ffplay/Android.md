LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libffplay
LOCAL_SRC_FILES := SDL_android_main.c ffplay2.c cmdutils.c

#depends
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../include/sdl

LOCAL_C_INCLUDES += $(LOCAL_PATH)/../include/ffmpeg \
					$(LOCAL_PATH)/../include/ffmpeg/android/$(TARGET_ARCH_ABI)

LOCAL_SHARED_LIBRARIES := avcodec-57-prebuilt avdevice-57-prebuilt avfilter-6-prebuilt avformat-57-prebuilt avutil-55-prebuilt avswresample-2-prebuilt swscale-4-prebuilt SDL2-prebuilt

LOCAL_LDLIBS += -lGLESv1_CM -lGLESv2 -llog -ljnigraphics -lz -landroid -lm -pthread

#finally
include $(BUILD_SHARED_LIBRARY)
