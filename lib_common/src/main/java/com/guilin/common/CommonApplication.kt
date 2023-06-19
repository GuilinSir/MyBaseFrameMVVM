package com.guilin.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.google.auto.service.AutoService
import com.guilin.base.app.ApplicationLifecycle
import com.guilin.base.app.BaseApplication
import com.guilin.base.app.InitDepend
import com.guilin.base.utils.ActivityStackManager
import com.guilin.base.utils.ProcessUtils
import com.guilin.base.utils.SpUtils

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:16 AM
 */
@AutoService(ApplicationLifecycle::class)
class CommonApplication : ApplicationLifecycle {
    /**
     * 项目当前的版本状态
     */
    val versionStatus: String by lazy {
        BaseApplication.context.getString(R.string.VERSION_STATUS)
    }

    companion object {
        // 全局CommonApplication
        @SuppressLint("StaticFieldLeak")
        lateinit var mCommonApplication: CommonApplication
    }

    /**
     * 同[Application.attachBaseContext]
     * @param context Context
     */
    override fun onAttachBaseContext(context: Context) {
        mCommonApplication = this
    }

    override fun onCreate(application: Application) {
    }

    override fun onTerminate(application: Application) {
    }

    /**
     * 主线程前台初始化
     * @return MutableList<() -> String> 初始化方法集合
     */
    override fun initByFrontDesk(): MutableList<() -> String> {
        val list = mutableListOf<() -> String>()
        // 以下只需要在主进程当中初始化 按需要调整
        if (ProcessUtils.isMainProcess(BaseApplication.context)) {
            list.add { initMMKV() }
            list.add { initARouter() }
            //list.add { initNetworkStateClient() }
        }
        //list.add { initTencentBugly() }
        return list
    }

    override fun initByBackstage() {
        //initX5WebViewCore()
    }


    /**
     * 腾讯 MMKV 初始化
     */
    private fun initMMKV(): String {
        val result = SpUtils.initMMKV(BaseApplication.application)
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
        ARouter.init(BaseApplication.application)
        return "ARouter -->> init complete"
    }

    /**
     * 腾讯TBS WebView X5 内核初始化
     */
//    private fun initX5WebViewCore() {
//        // dex2oat优化方案
//        val map = HashMap<String, Any>()
//        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
//        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
//        QbSdk.initTbsSettings(map)
//
//        // 允许使用非wifi网络进行下载
//        QbSdk.setDownloadWithoutWifi(true)
//
//        // 初始化
//        QbSdk.initX5Environment(BaseApplication.context, object : PreInitCallback {
//
//            override fun onCoreInitFinished() {
//                Log.d("ApplicationInit", " TBS X5 init finished")
//            }
//
//            override fun onViewInitFinished(p0: Boolean) {
//                // 初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核
//                Log.d("ApplicationInit", " TBS X5 init is $p0")
//            }
//        })
//    }
}