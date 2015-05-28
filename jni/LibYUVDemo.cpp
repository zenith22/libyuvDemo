#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include "libyuv.h"
#include <android/log.h>

#define LOG_TAG "YUVDemo"
#define LOGI(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define printf(...) LOGI(__VA_ARGS__)

using namespace libyuv;

extern "C" {
JNIEXPORT void Java_com_demo_libyuvdemo_MainActivity_callLibYUV(JNIEnv* env, jobject thiz) {
	libyuv::RotationMode mode = libyuv::kRotate180;
	printf("rotation mode selected = %d",mode);
}

JNIEXPORT jstring Java_com_demo_libyuvdemo_MainActivity_stringFromJNI(
		JNIEnv* env, jobject thiz) {
#if defined(__arm__)
#if defined(__ARM_ARCH_7A__)
#if defined(__ARM_NEON__)
#if defined(__ARM_PCS_VFP)
#define ABI "armeabi-v7a/NEON (hard-float)"
#else
#define ABI "armeabi-v7a/NEON"
#endif
#else
#if defined(__ARM_PCS_VFP)
#define ABI "armeabi-v7a (hard-float)"
#else
#define ABI "armeabi-v7a"
#endif
#endif
#else
#define ABI "armeabi"
#endif
#elif defined(__i386__)
#define ABI "x86"
#elif defined(__x86_64__)
#define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
#define ABI "mips64"
#elif defined(__mips__)
#define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
#define ABI "unknown"
#endif

	return env->NewStringUTF("Hello from JNI !  Compiled with ABI " ABI ".");
}
}
