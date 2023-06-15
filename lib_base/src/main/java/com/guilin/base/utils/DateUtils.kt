package com.guilin.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @description: 时间工具类
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/14 3:50 PM
 */
object DateUtils {
    /**
     * 获取时间格式化String
     * @param timestamp 时间戳
     * @param dateFormat 日期格式
     */
    fun getDateFormatString(timestamp: Long, dateFormat: String): String =
        SimpleDateFormat(dateFormat, Locale.CHINESE).format(Date(timestamp))

    /**
     * 将固定格式[dateFormat]的时间字符串[dateString]转换为时间值
     */
    fun getDateStringToDate(dateString: String, dateFormat: String): Long {
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.CHINESE)
        var date = Date()
        try {
            date = simpleDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.time
    }
}