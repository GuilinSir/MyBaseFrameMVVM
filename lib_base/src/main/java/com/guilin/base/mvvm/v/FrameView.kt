package com.guilin.base.mvvm.v

/**
 * @description: view层基类抽象
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/15 9:02 AM
 */
interface FrameView {
    /**
     * 初始化view
     */
    fun initView()

    /**
     * 订阅LiveData
     */
    fun initViewObserve()
}