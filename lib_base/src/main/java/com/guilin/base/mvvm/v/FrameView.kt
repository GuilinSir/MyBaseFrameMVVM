package com.guilin.base.mvvm.v

import androidx.viewbinding.ViewBinding

/**
 * @description: view层基类抽象
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/15 9:02 AM
 */
interface FrameView<VB : ViewBinding> {
    /**
     * 初始化view
     */
    fun VB.initView()

    /**
     * 订阅LiveData
     */
    fun initLiveDataObserve()

    /**
     * 初始化页面创建
     */
    fun initRequestData()

    /**
     * 页面是否重建，fragment被回收重新展示的时候为true，系统环境发生变化activity重新创建时为true
     */
    fun isRecreate(): Boolean
}