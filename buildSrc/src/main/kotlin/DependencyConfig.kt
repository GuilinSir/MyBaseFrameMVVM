package com.guilin.mybaseframemvvm.build

/**
 * 依赖库管理
 */
object DependencyConfig {
    object Version {
        // AndroidX--------------------------------------------------------------
        const val AppCompat = "1.4.1"
        const val CoreKtx = "1.7.0"
        const val ConstraintLayout = "2.1.3"                // 约束布局
        const val TestExtJunit = "1.1.3"
        const val TestEspresso = "3.4.0"
        const val ActivityKtx = "1.5.0"
        const val FragmentKtx = "1.5.0"
        const val MultiDex = "2.0.1"
        const val ViewBinding = "8.0.2"

        // Android---------------------------------------------------------------
        const val Junit = "4.13"

        // Kotlin----------------------------------------------------------------
        const val Kotlin = "1.8.22"
        const val Coroutines = "1.4.3"                      // 协程

        // JetPack---------------------------------------------------------------
        const val Lifecycle = "2.5.0"
        const val Hilt = "2.46.1"                    // Hilt 依赖注入

        // GitHub----------------------------------------------------------------
        const val OkHttp = "4.9.0"                          // OkHttp
        const val OkHttpInterceptorLogging = "4.9.1"        // OkHttp 请求Log拦截器
        const val Retrofit = "2.9.0"                        // Retrofit
        const val RetrofitConverterGson = "2.9.0"           // Retrofit Gson 转换器
        const val Gson = "2.8.7"                            // Gson
        const val MMKV = "1.2.9"                            // 腾讯 MMKV 替代SP
        const val AutoSize = "1.2.1"                        // 屏幕适配
        const val Glide = "4.12.0"                          // Glide
        const val ARoute = "1.5.2"                          // 阿里路由
        const val ARouteCompiler = "1.5.2"                  // 阿里路由 APT
        const val RecyclerViewAdapter = "3.0.4"             // RecyclerViewAdapter
        const val StatusBar = "1.5.1"                       // 状态栏
        const val EventBus = "3.2.0"                        // 事件总线
        const val PermissionX = "1.4.0"                     // 权限申请
        const val LeakCanary = "2.11"                        // 检测内存泄漏
        const val Chuck = "1.1.0"                           // OkHttp 请求信息拦截器(UI)
        const val AutoService = "1.0"                     // 自动生成SPI暴露服务文件

    }

    object AndroidX {
        const val AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val AppCompat = "androidx.appcompat:appcompat:${Version.AppCompat}"
        const val CoreKtx = "androidx.core:core-ktx:${Version.CoreKtx}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.ConstraintLayout}"
        const val TestExtJunit = "androidx.test.ext:junit:${Version.TestExtJunit}"
        const val TestEspresso = "androidx.test.espresso:espresso-core:${Version.TestEspresso}"
        const val ActivityKtx = "androidx.activity:activity-ktx:${Version.ActivityKtx}"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:${Version.FragmentKtx}"
        const val MultiDex = "androidx.multidex:multidex:${Version.MultiDex}"
        const val ViewBinding = "androidx.databinding:viewbinding:${Version.ViewBinding}"
    }

    object Android {
        const val Junit = "junit:junit:${Version.Junit}"
    }

    object JetPack {
//        const val LifecycleViewModel =
//            "androidx.lifecycle:lifecycle-viewmodel:${Version.LifecycleViewModel}"
//        const val LifecycleRuntimeKtx =
//            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LifecycleRuntimeKtx}"
//        const val LifecycleViewModelKtx =
//            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LifecycleViewModelKtx}"

        const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Lifecycle}"
        const val ViewModelSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.Lifecycle}"
        const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Lifecycle}"
        const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Lifecycle}"
        const val LifecycleCompilerAPT =
            "androidx.lifecycle:lifecycle-compiler:${Version.Lifecycle}"

        const val HiltCore = "com.google.dagger:hilt-android:${Version.Hilt}"
        const val HiltApt = "com.google.dagger:hilt-compiler:${Version.Hilt}"

    }

    object Kotlin {
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin}"
        const val CoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutines}"
        const val CoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutines}"
    }

    object GitHub {
        const val OkHttp = "com.squareup.okhttp3:okhttp:${Version.OkHttp}"
        const val OkHttpInterceptorLogging =
            "com.squareup.okhttp3:logging-interceptor:${Version.OkHttpInterceptorLogging}"
        const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
        const val RetrofitConverterGson =
            "com.squareup.retrofit2:converter-gson:${Version.RetrofitConverterGson}"
        const val Gson = "com.google.code.gson:gson:${Version.Gson}"
        const val MMKV = "com.tencent:mmkv-static:${Version.MMKV}"
        const val AutoSize = "com.github.JessYanCoding:AndroidAutoSize:${Version.AutoSize}"
        const val Glide = "com.github.bumptech.glide:glide:${Version.Glide}"
        const val GlideCompiler = "com.github.bumptech.glide:compiler:${Version.Glide}"
        const val ARoute = "com.alibaba:arouter-api:${Version.ARoute}"
        const val ARouteCompiler = "com.alibaba:arouter-compiler:${Version.ARouteCompiler}"
        const val RecyclerViewAdapter =
            "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.RecyclerViewAdapter}"
        const val StatusBar = "com.jaeger.statusbarutil:library:${Version.StatusBar}"
        const val EventBus = "org.greenrobot:eventbus:${Version.EventBus}"
        const val EventBusAPT = "org.greenrobot:eventbus-annotation-processor:${Version.EventBus}"
        const val PermissionX = "com.permissionx.guolindev:permissionx:${Version.PermissionX}"
        const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.LeakCanary}"
        const val Chuck = "com.readystatesoftware.chuck:library:${Version.Chuck}"
        const val ChuckNoOp = "com.readystatesoftware.chuck:library-no-op:${Version.Chuck}"
        const val AutoService = "com.google.auto.service:auto-service:${Version.AutoService}"
        const val AutoServiceAnnotations =
            "com.google.auto.service:auto-service-annotations:${Version.AutoService}"
    }
}