//
// Created by harsh.p on 28-08-2018.
//

#include <jni.h>
/* Header for class com_enmingx_server_NDKServer */

#ifndef _Included_com_enmingx_server_NDKServer
#define _Included_com_enmingx_server_NDKServer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_enmingx_server_NDKServer
 * Method:    getMyString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_testproject_MainActivity_getMyString
        (JNIEnv *, jobject);

/*
 * Class:     com_enmingx_server_NDKServer
 * Method:    startServer
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_testproject_MainActivity_startServer
        (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif