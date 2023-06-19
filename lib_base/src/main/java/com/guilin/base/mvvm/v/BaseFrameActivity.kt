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
import com.guilin.base.utils.ViewRecreateHelper
import java.lang.reflect.ParameterizedType

/**
 * @description:Activity基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:34 AM
 */
abstract class BaseFrameActivity<VB : ViewBinding> : AppCompatActivity(), FrameView<VB> {
    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewBindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    /**
     * activity页面重建帮助类
     */
    private var mStatusHelper: ViewRecreateHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        //处理保存的装填
        mStatusHelper?.onRestoreInstanceStatus(savedInstanceState)
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

    override fun isRecreate(): Boolean = mStatusHelper?.isRecreate ?: false

    override fun onSaveInstanceState(outState: Bundle) {
        if (mStatusHelper == null) {
            //仅当触发重建需要保存状态时创建对象
            mStatusHelper = ViewRecreateHelper(outState)
        } else {
            mStatusHelper?.onSaveInstanceState(outState)
        }
        super.onSaveInstanceState(outState)
    }

}