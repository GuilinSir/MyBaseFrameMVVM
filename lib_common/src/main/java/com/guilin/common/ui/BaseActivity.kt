package com.guilin.common.ui

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.guilin.base.mvvm.v.BaseFrameActivity
import com.guilin.base.utils.AndroidBugFixUtils

/**
 * @description:项目相关的Activity基类
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 2:18 PM
 */
abstract class BaseActivity<VB : ViewBinding> : BaseFrameActivity<VB>() {
    override fun onDestroy() {
        super.onDestroy()
        // 解决某些特定机型会触发的Android本身的Bug
        AndroidBugFixUtils().fixSoftInputLeaks(this)
    }
}