package com.guilin.main.m

import com.guilin.base.mvvm.m.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 3:58 PM
 */
class MainRepository : BaseRepository() {

    /**
     * 模拟请求或读取数据库
     */
    suspend fun getString(): Flow<String> {
        val flowRequest = flowRequest<String> {
            emit("嘿嘿嘿")
        }
        return flowRequest
    }
}