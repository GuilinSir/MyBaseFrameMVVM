package com.guilin.main.di

import com.guilin.main.net.MainApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

/**
 * @description:全局作用域的Main组件网络接口代理依赖注入模块
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/19 10:09 AM
 */
@Module
@InstallIn(SingletonComponent::class)
class DIMainNetServiceModule {
    fun provideMainApiService(retrofit:Retrofit):MainApiService{
        return retrofit.create(MainApiService::class.java)
    }
}