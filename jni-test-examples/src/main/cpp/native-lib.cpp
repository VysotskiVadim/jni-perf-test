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
    {"getNativeStringFromArray", "(I)Ljava/lang/String;", (void *) provideStringFromArray},
    {"initStringArray", "(I)V", (void *) initializeArrayOfStrings}
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