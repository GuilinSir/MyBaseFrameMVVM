package com.guilin.mybaseframemvvm

import androidx.multidex.MultiDex
import com.guilin.common.CommonApplication

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 10:38 AM
 */
class AppApplication :CommonApplication(){
    override fun initialize() {
        MultiDex.install(this)
        super.initialize()
    }
}