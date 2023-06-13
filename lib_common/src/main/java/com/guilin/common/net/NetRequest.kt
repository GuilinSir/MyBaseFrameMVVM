package com.guilin.common.net

import com.guilin.common.net.api.ApiCommonService
import com.guilin.common.net.api.ApiHomeService

/**
 * @description:  对ApiService动态代理对象统一管理
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/13 2:01 PM
 */
object NetRequest {
    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator.create(ApiCommonService::class.java)
    }

    val homeService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator.create(ApiHomeService::class.java)
    }
}