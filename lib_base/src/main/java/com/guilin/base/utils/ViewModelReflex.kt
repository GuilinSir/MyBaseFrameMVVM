package com.guilin.base.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import java.lang.RuntimeException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType
import java.util.Objects

/**
 * @description: 用于反射获取 ViewModel
 *
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/15 9:43 AM
 */
object ViewModelReflex {

    /**
     * 反射获取ViewModel
     *
     * @param <VM>   ViewModel实现类
     * @param aClass 当前class
     * @param owner  生命周期管理
     * @return ViewModel实例
    </VM> */
    fun <VM : ViewModel> reflexViewModel(aClass: Class<*>, owner: ViewModelStoreOwner): VM {
        try {
            val actualTypeArguments =
                (Objects.requireNonNull(aClass.genericSuperclass) as ParameterizedType).actualTypeArguments
            for (i in actualTypeArguments.indices) {
                //val tClass = actualTypeArguments[i] as Class<VM>
                val tClass: Class<Any>
                try {
                    tClass = actualTypeArguments[i] as Class<Any>
                } catch (e: Exception) {
                    continue
                }
                if (ViewModel::class.java.isAssignableFrom(tClass)) {
                    return ViewModelProvider(owner)[tClass as Class<VM>]
                }
            }
            return reflexViewModel<VM>(aClass.superclass, owner)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw RuntimeException("ViewModel初始化失败")
    }

    /**
     * 反射获取ViewModel,这个方法只提供给fragment使用.
     * 如果fragment的父Activity有相同的ViewModel 那么生成的ViewModel将会是同一个实例，做到Fragment与Activity的数据同步,
     * 或者说是同一个Activity中的多个Fragment同步使用用一个ViewModel达到数据之间的同步。
     *
     * @param <VM>     ViewModel实现类
     * @param aClass   当前class
     * @param fragment fragment  调用 [Fragment.requireActivity] 方法
     * @return ViewModel实例
    </VM> */
    fun <VM : ViewModel> reflexViewModelShared(aClass: Class<*>, fragment: Fragment): VM {
        try {
            val actualTypeArguments =
                (Objects.requireNonNull(aClass.genericSuperclass) as ParameterizedType).actualTypeArguments
            for (i in actualTypeArguments.indices) {
                //val tClass = actualTypeArguments[i] as Class<VM>
                val tClass: Class<Any>
                try {
                    tClass = actualTypeArguments[i] as Class<Any>
                } catch (e: Exception) {
                    continue
                }
                if (ViewModel::class.java.isAssignableFrom(tClass)) {
                    return ViewModelProvider(fragment.requireActivity())[tClass as Class<VM>]
                }
            }
            return reflexViewModelShared<VM>(aClass.superclass, fragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw RuntimeException("ViewModel初始化失败")
    }
}