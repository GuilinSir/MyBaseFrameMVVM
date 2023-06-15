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
import java.lang.reflect.ParameterizedType

/**
 * @description:Fragment基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 2:11 PM
 */
abstract class BseFrameFragment<VB : ViewBinding, VM : ViewModel>(private val vmClass: Class<VM>) :
    Fragment() {
    private val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        //ViewModelProvider(this).get(vmClass)
        getViewModelReflex()
    }
    private val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        //initViewBinding()
        getViewBindingReflex()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.register(this)
        initView()
    }

    //abstract fun initViewBinding(): VB
    abstract fun initView()
    abstract fun initViewObserve()
    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java))
            EventBusUtils.unRegister(this)
        super.onDestroy()
    }

    /**
     * 反射初始化ViewBinding
     */
    private fun getViewBindingReflex(): VB {
        val tClass: Class<VB> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val infater = tClass.getMethod("inflate", LayoutInflater::class.java)
        return infater.invoke(null, layoutInflater) as VB
    }
    /**
     * 反射初始化ViewModel
     */
    private fun getViewModelReflex(): VM {
        //init ViewModel | getActualTypeArguments [0]=是第一个泛型参数 | [1] = 是类的第二个泛型参数
        val tClass: Class<VM> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        return ViewModelProvider(this).get(tClass)
    }

}