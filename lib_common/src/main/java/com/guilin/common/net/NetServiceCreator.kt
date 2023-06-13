package com.guilin.common.net

import android.os.Build
import com.guilin.common.BuildConfig
import com.guilin.common.constant.NetUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @description:Retrofit动态代理对象获取封装
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/13 2:04 PM
 */
object NetServiceCreator {
    private const val BASE_URL = NetUrl.url
    private const val CONNECT_TIME_OUT = 15L
    private const val READ_TIME_OUT = 20L
    private val BODY by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val NONE by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    private val okHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)//重连超时
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)//读取超时
            .addInterceptor(if (BuildConfig.DEBUG) BODY else NONE)//请求日志拦截器
            .retryOnConnectionFailure(true)//失败重连
            .build()
    }

    private val retorfit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())//Gson转换器
            .client(okHttpClient)
            .build()
    }

    /**
     * 获取service动态代理对象
     * @param serviceClass 接口Class对象
     */
    fun <T> create(serviceClass: Class<T>): T = retorfit.create(serviceClass)

    /**
     * 获取service动态代理对象
     * 范型实化
     */
    inline fun <reified T> create(): T = create(T::class.java)

}