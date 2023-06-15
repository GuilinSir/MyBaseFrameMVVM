package com.guilin.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils
import java.lang.reflect.ParameterizedType

/**
 * @description:Activity基类 与项目无关
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 11:34 AM
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : ViewModel> :
    AppCompatActivity(),FrameView {
    val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        //initViewBinding()
        getViewBindingReflex()
    }


    val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        //ViewModelProvider(this).get(vmClass)
        getViewModelReflex()
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

    //abstract fun initViewBinding(): VB
    //abstract fun initView()
    //abstract fun initViewObserve()
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
        val infater = tClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
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