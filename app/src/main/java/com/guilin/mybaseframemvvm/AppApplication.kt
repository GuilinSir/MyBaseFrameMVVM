package com.guilin.mybaseframemvvm

import androidx.multidex.MultiDex
import com.guilin.common.CommonApplication
import org.greenrobot.eventbus.EventBus

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 10:38 AM
 */
class AppApplication : CommonApplication() {
    override fun onCreate() {
        super.onCreate()
        // 开启EventBusAPT,优化反射效率 当组件作为App运行时需要将添加的Index注释掉 因为找不到对应的类了
        EventBus.builder()
            //.addIndex(MainEventIndex())
            .installDefaultEventBus()
    }
}