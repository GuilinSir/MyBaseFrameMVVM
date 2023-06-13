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
class AppApplication :CommonApplication(){
    override fun initialize() {
        MultiDex.install(this)
        // 开启EventBusAPT,优化反射效率
        EventBus.builder()
            //.addIndex(MainEventIndex())
            .installDefaultEventBus()
        super.initialize()
    }
}