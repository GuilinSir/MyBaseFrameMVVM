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
 * @description: 用于反射获取  ViewBinding
 *
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/15 9:43 AM
 */
object ViewBindingReflex {
    /**
     * 反射获取ViewBinding
     *
     * @param <V>    ViewBinding 实现类
     * @param aClass 当前类
     * @param from   layouinflater
     * @return viewBinding实例
    </V> */
    fun <V : ViewBinding> reflexViewBinding(
        aClass: Class<*>,
        from: LayoutInflater?): V {
        try {
            val actualTypeArguments =
                (Objects.requireNonNull(aClass.genericSuperclass) as ParameterizedType).actualTypeArguments
            for (i in actualTypeArguments.indices) {
                //val tClass = actualTypeArguments[i] as Class<V>
                val tClass: Class<Any>
                try {
                    tClass = actualTypeArguments[i] as Class<Any>
                } catch (e: Exception) {
                    continue
                }

                if (ViewBinding::class.java.isAssignableFrom(tClass)) {
                    val inflate = tClass.getMethod("inflate", LayoutInflater::class.java)
                    return inflate.invoke(null, from) as V
                }
            }
            return reflexViewBinding<V>(aClass.superclass, from)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        throw RuntimeException("ViewBinding初始化失败")
    }

    /**
     * 反射获取ViewBinding
     */
    fun <V : ViewBinding> reflexViewBinding(
        aClass: Class<*>,
        from: LayoutInflater?,
        viewGroup: ViewGroup?,
        b: Boolean
    ): V {
        try {
            val actualTypeArguments =
                (Objects.requireNonNull(aClass.genericSuperclass) as ParameterizedType).actualTypeArguments
            for (i in actualTypeArguments.indices) {
                //val tClass = actualTypeArguments[i] as Class<V>
                val tClass: Class<Any>
                try {
                    tClass = actualTypeArguments[i] as Class<Any>
                } catch (e: Exception) {
                    continue
                }

                if (ViewBinding::class.java.isAssignableFrom(tClass)) {
                    val inflate = tClass.getDeclaredMethod(
                        "inflate",
                        LayoutInflater::class.java,
                        ViewGroup::class.java,
                        Boolean::class.javaPrimitiveType
                    )
                    return inflate.invoke(null, from, viewGroup, b) as V
                }
            }
            return reflexViewBinding<ViewBinding>(aClass.superclass, from, viewGroup, b) as V
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        throw RuntimeException("ViewBinding初始化失败")
    }
}