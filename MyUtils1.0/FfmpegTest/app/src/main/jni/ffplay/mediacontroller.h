/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class wjs_player_control_MediaController */

#ifndef _Included_wjs_player_control_MediaController
#define _Included_wjs_player_control_MediaController
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     wjs_player_control_MediaController
 * Method:    play
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_play
  (JNIEnv *, jobject, jstring);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    pause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_pause
  (JNIEnv *, jobject);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    resume
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_resume
  (JNIEnv *, jobject);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    seek
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_seek
  (JNIEnv *, jobject, jlong);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    getCurrentPosition
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_wjs_player_control_MediaController_getCurrentPosition
  (JNIEnv *, jobject);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    getDuration
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_wjs_player_control_MediaController_getDuration
  (JNIEnv *, jobject);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    fastForward
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_fastForward
  (JNIEnv *, jobject, jint);

/*
 * Class:     wjs_player_control_MediaController
 * Method:    fastRewind
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wjs_player_control_MediaController_fastRewind
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
