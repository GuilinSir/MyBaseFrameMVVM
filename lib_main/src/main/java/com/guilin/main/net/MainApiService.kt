package com.guilin.main.net

import com.guilin.common.bean.TestBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/19 10:10 AM
 */
interface MainApiService {
    @GET
    suspend fun getData(
        @Query("test") test: String
    ): TestBean
}