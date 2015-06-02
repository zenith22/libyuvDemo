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
JNIEXPORT jbyteArray Java_com_demo_libyuvdemo_MainActivity_ConvertToI420(
		JNIEnv* env, jobject thiz, jbyteArray nv21, int w, int h, int type) {

	int nv21Len = env->GetArrayLength(nv21);
	unsigned char* nv21Buf = new unsigned char[nv21Len];
	env->GetByteArrayRegion(nv21, 0, nv21Len,
			reinterpret_cast<jbyte*>(nv21Buf));

	int Ysize = w * h;
	size_t src_size = Ysize * 1.5;

	unsigned char* I420 = new unsigned char[nv21Len];

	unsigned char* pDstY = I420;
	unsigned char* pDstU = I420 + Ysize;
	unsigned char* pDstV = pDstU + (Ysize / 4);

	int retVal = 0;

	if(type == 1){
		libyuv::RotationMode mode = libyuv::kRotate0;

		retVal = libyuv::ConvertToI420(nv21Buf, src_size, pDstY, w, pDstU, w / 2, pDstV,
				w / 2, 0, 0, w, h, w, h, mode, libyuv::FOURCC_NV21);
	}else if(type == 2){
		libyuv::RotationMode mode = libyuv::kRotate0;

		retVal = libyuv::ConvertToI420(nv21Buf, src_size, pDstY, w, pDstU, w / 2, pDstV,
				w / 2, 0, 0, w, -h, w, h, mode, libyuv::FOURCC_NV21);
	}else if(type == 3){
		libyuv::RotationMode mode = libyuv::kRotate90;

		retVal = libyuv::ConvertToI420(nv21Buf, src_size, pDstY, h, pDstU, h / 2, pDstV,
				h / 2, 0, 0, w, h, w, h, mode, libyuv::FOURCC_NV21);
	}else if(type == 4){
		libyuv::RotationMode mode = libyuv::kRotate90;

		retVal = libyuv::ConvertToI420(nv21Buf, src_size, pDstY, h, pDstU, h / 2, pDstV,
				h / 2, 0, 0, w, -h, w, h, mode, libyuv::FOURCC_NV21);
	}
	/*libyuv::ConvertToI420(const uint8* src_frame, size_t src_size,
	 uint8* dst_y, int dst_stride_y,
	 uint8* dst_u, int dst_stride_u,
	 uint8* dst_v, int dst_stride_v,
	 int crop_x, int crop_y,
	 int src_width, int src_height,
	 int crop_width, int crop_height,
	 enum RotationMode rotation,
	 uint32 format);*/

	printf("type = %d and post convertI420 retVal = %d",type, retVal);


	jbyteArray I420byte = env->NewByteArray(nv21Len);
	env->SetByteArrayRegion(I420byte,0,nv21Len, reinterpret_cast<jbyte*>(I420));

	if(I420){
		delete [] I420;
	}

	if(nv21Buf){
		delete [] nv21Buf;
	}

	return I420byte;
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
