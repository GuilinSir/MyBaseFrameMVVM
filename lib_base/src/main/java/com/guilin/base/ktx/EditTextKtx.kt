package com.guilin.base.ktx

import android.text.InputFilter
import android.widget.EditText

/**
 * @description:  EditText相关扩展方法
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/13 8:53 AM
 */

/**
 * 过滤掉空格和回车
 */
fun EditText.filterBlankAndCarriageReturn() {
    this.filters =
        arrayOf(InputFilter { source, _, _, _, _, _ -> if (source == " " || source == "\n") "" else null })
}