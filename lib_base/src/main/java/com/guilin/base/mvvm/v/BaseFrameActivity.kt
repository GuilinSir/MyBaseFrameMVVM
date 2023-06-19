package com.guilin.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.ViewBindingReflex
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils
import com.guilin.base.utils.ViewModelReflex
import java.lang.reflect.ParameterizedType

/**
 * @description:Activity基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:34 AM
 */
abstract class BaseFrameActivity<VB : ViewBinding> : AppCompatActivity(),FrameView<VB> {
    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewBindingReflex.reflexViewBinding(javaClass,layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.register(this)
        mBinding.initView()
        initLiveDataObserve()
        initRequestData()
    }


    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.unRegister(this)
        super.onDestroy()
    }




}