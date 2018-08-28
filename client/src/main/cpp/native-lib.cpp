#include <jni.h>
#include <string>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <stdio.h>
#include <netdb.h>
#include <unistd.h>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_testproject_client_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jstring

JNICALL
Java_com_testproject_MainActivity_initiateTcpConnection(JNIEnv *env, jobject javaThis,jstring ipAddress) {
    int tcp_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (tcp_socket < 0) {
        return (env)->NewStringUTF("ERROR CREATING SOCKET");
    }
    const char *server_host = env->GetStringUTFChars(ipAddress, 0);
    unsigned short server_port = 1309;

    struct sockaddr_in server_tcp_addr;
    server_tcp_addr.sin_family = AF_INET;
    server_tcp_addr.sin_port = htons(server_port);
    struct hostent *hostp = gethostbyname(server_host);
    memcpy(&server_tcp_addr.sin_addr.s_addr, hostp->h_addr, hostp->h_length);
    socklen_t slen = sizeof(server_tcp_addr);
    if (connect(tcp_socket, (struct sockaddr *) &server_tcp_addr, slen) < 0) { //fails here
        close(tcp_socket);
        return (env)->NewStringUTF("ERROR CONNECTING TO SERVER");
    }

    char *message = const_cast<char *>("hello from android!");
    send(tcp_socket, &message, sizeof(message), 0);

    return (env)->NewStringUTF("TCP message sent!");
}