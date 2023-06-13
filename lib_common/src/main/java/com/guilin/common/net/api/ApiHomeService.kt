package com.guilin.common.net.api

import com.guilin.common.bean.TestBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @description:home接口
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/13 1:57 PM
 */
interface  ApiHomeService {
    @GET()
    suspend fun getTestData(@Query("test") test: String): TestBean
}