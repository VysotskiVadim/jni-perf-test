#include <jni.h>
#include <string>
#include <android/log.h>

static std::string** arrayOfStrings;

static jstring provideStringFromArray(JNIEnv *env, jobject thiz, jint index) {
    return env->NewStringUTF(arrayOfStrings[index]->c_str());
}

static void initializeArrayOfStrings(JNIEnv *env, jobject thiz, jint arraySize) {
    arrayOfStrings = new std::string*[arraySize];
    for (int i = 0; i < arraySize; ++i) {
        arrayOfStrings[i] = new std::string("test string " + std::to_string(i));
    }
}

static int* intArray;

static jint provideIntFromArray(JNIEnv *env, jobject thiz, jint index) {
    return intArray[index];
}

static jint provideIntFromArrayFast(jint index) {
    return intArray[index];
}

static void initIntArray(JNIEnv *env, jobject thiz, jint arraySize) {
    intArray = new int32_t[arraySize];
    for (int i = 0; i < arraySize; ++i) {
        intArray[i] = i;
    }
}

static double* doubleArray;

static jdouble provideDoubleFromArray(JNIEnv *env, jobject thiz, jint index) {
    return doubleArray[index];
}

static jint provideDoubleFromArrayFast(jint index) {
    return doubleArray[index];
}

static void initDoubleArray(JNIEnv *env, jobject thiz, jint arraySize) {
    doubleArray = new double[arraySize];
    for (int i = 0; i < arraySize; ++i) {
        doubleArray[i] = (double) i + 0.1;
    }
}

static uint8_t* byteBuffer;
static jobject javaByteBufferWrapper;

static void initByteBuffer(JNIEnv *env, jobject thiz, jint bufferSize) {
    void* buffer = malloc(bufferSize);
    byteBuffer = static_cast<uint8_t*>(buffer);
    javaByteBufferWrapper = env->NewDirectByteBuffer(buffer, bufferSize);
}

static jobject getJavaByteBuffer(JNIEnv *env, jobject thiz) {
    return javaByteBufferWrapper;
}

// Registration

static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

// this is the path of java package for the wrapper class.
static const char *nativeDataProviderClassName = "dev/vadzimv/jniperftest/NativeDataProvider";

static JNINativeMethod constants_methods[] = {
    // ------- java-function-name --------- jni-signature -------- cpp-function-ptr--
    {"initStringArray", "(I)V", (void *) initializeArrayOfStrings},
    {"getNativeStringFromArray", "(I)Ljava/lang/String;", (void *) provideStringFromArray},
    {"getFastNativeStringFromArray", "(I)Ljava/lang/String;", (void *) provideStringFromArray},

    {"initIntArray", "(I)V", (void *) initIntArray},
    {"getIntFromArray", "(I)I", (void *) provideIntFromArray},
    {"getIntFromArrayFastNative", "(I)I", (void *) provideIntFromArray},
    {"getIntFromArrayCriticalNative", "(I)I", (void *) provideIntFromArrayFast},

    {"initDoubleArray", "(I)V", (void *) initDoubleArray},
    {"getDoubleFromArray", "(I)D", (void *) provideDoubleFromArray},
    {"getDoubleFromArrayFastNative", "(I)D", (void *) provideDoubleFromArray},
    {"getDoubleFromArrayCriticalNative", "(I)D", (void *) provideDoubleFromArrayFast},

    {"initByteBuffer", "(I)V", (void *) initByteBuffer},
    {"getByteBuffer", "()Ljava/nio/ByteBuffer;", (void *) getJavaByteBuffer},
};

static int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env,
                               nativeDataProviderClassName ,
                               constants_methods,
                               std::size(constants_methods))) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

jint JNI_OnLoad(JavaVM *vm, void *reserved){
    JNIEnv* env;
    vm->GetEnv((void**)&env, JNI_VERSION_1_4);

    if (registerNatives(env) == JNI_TRUE) {
        return JNI_VERSION_1_4;
    } else {
        return -1;
    }
}