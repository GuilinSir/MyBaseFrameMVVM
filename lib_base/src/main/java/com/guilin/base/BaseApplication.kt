package com.guilin.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.SpUtils
import me.jessyan.autosize.AutoSizeConfig

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:19 AM
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    protected open fun initialize() {
        // 腾讯 MMKV 初始化
        SpUtils.initMMKV(this)

        // 阿里路由 ARouter 初始化
        if (BuildConfig.DEBUG) {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        // 屏幕适配 AndroidAutoSize 以横屏高度为基准进行适配
        //AutoSizeConfig.getInstance().isBaseOnWidth = false

    }


}