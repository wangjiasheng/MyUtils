LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libffplay
#depends
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../include/sdl

LOCAL_C_INCLUDES += $(LOCAL_PATH)/../include/ffmpeg \
					$(LOCAL_PATH)/../include/ffmpeg/android/$(TARGET_ARCH_ABI)


LOCAL_SRC_FILES := kuplayer_android.c native.cpp SDL_android_main.cpp

LOCAL_LDLIBS += -lGLESv1_CM -lGLESv2 -llog -ljnigraphics -lz -landroid -lm -pthread

LOCAL_LDLIBS += avcodec-57-prebuilt avdevice-57-prebuilt avfilter-6-prebuilt avformat-57-prebuilt avutil-55-prebuilt avswresample-2-prebuilt swscale-4-prebuilt SDL2-prebuilt
include $(BUILD_SHARED_LIBRARY)
