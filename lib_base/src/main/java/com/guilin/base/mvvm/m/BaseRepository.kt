package com.guilin.base.mvvm.m

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

/**
 * @description:仓库层 Repository 基类
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 2:14 PM
 */
open class BaseRepository {
    /**
     * 发起请求封装
     * @param requestBlock 请求的整体逻辑
     * @return Flow<T>
     */
    protected suspend fun <T> flowRequest(requestBlock: suspend FlowCollector<T>.() -> Unit) =
        flow {
            requestBlock()
        }.flowOn(Dispatchers.IO) // 通过 flowOn 切换到 io 线程
}