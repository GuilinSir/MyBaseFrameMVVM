package com.guilin.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.BaseApplication
import com.guilin.base.utils.ActivityStackManager
import com.guilin.base.utils.SpUtils

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:16 AM
 */
open class CommonApplication : BaseApplication(), Application.ActivityLifecycleCallbacks {
    /**
     * 项目当前的版本状态
     */
    val versionStatus: String by lazy {
        getString(R.string.VERSION_STATUS)
    }

    override fun onCreate() {
        super.onCreate()
        // 全局监听 Activity 生命周期
        registerActivityLifecycleCallbacks(this)
    }

    override fun initByFrontDesk(): InitDepend {
        val worker = mutableListOf<() -> String>()
        worker.add { initMMKV() }
        worker.add { initARouter() }
        //worker.add { initTencentBugly() }
        return InitDepend(null, worker)
    }

    override suspend fun initByBackstage() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        ActivityStackManager.addActivityToStack(activity)
    }

    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        ActivityStackManager.popActivityToStack(activity)
    }

    /**
     * 腾讯 MMKV 初始化
     */
    private fun initMMKV(): String {
        val result = SpUtils.initMMKV(this)
        return "MMKV -->> $result"
    }

    /**
     * 阿里路由 ARouter 初始化
     */
    private fun initARouter(): String {
        // 测试环境下打开ARouter的日志和调试模式 正式环境需要关闭
        if (versionStatus == "VERSION_STATUS_ALPHA" || versionStatus == "VERSION_STATUS_BETA") {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        return "ARouter -->> init complete"
    }
}