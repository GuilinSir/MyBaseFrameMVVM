package com.guilin.base.mvvm.v

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils

/**
 * @description:Activity基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:34 AM
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : ViewModel>(private val vmClass: Class<VM>) :
    AppCompatActivity() {
    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        initViewBinding()
    }
    val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this).get(vmClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)

        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.register(this)
        initView()
        initViewObserve()
    }

    abstract fun initViewBinding(): VB
    abstract fun initView()
    abstract fun initViewObserve()
    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.unRegister(this)
        super.onDestroy()
    }

}