package com.guilin.base.ktx

import android.view.View

/**
 * @description:  View相关的扩展方法
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/13 8:59 AM
 */
fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}