package com.guilin.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils
import com.guilin.base.utils.ViewBindingReflex
import com.guilin.base.utils.ViewModelReflex
import com.guilin.base.utils.ViewRecreateHelper
import java.lang.reflect.ParameterizedType

/**
 * @description:Fragment基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 2:11 PM
 */
abstract class BseFrameFragment<VB : ViewBinding > :
    Fragment(), FrameView<VB> {

    private val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewBindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }
    /**
     * fragment页面重建帮助类
     */
    private var mStatusHelper: ViewRecreateHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //处理恢复
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

    /**
     * 页面可能重建的时候回执行此方法，进行当前页面状态保存
     */
    override fun onSaveInstanceState(outState: Bundle) {
        if (mStatusHelper == null) {
            //仅当触发重建需要保存状态时创建对象
            mStatusHelper = ViewRecreateHelper(outState)
        } else {
            mStatusHelper?.onSaveInstanceState(outState)
        }
        super.onSaveInstanceState(outState)
    }


    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.unRegister(this)
        super.onDestroy()
    }

}