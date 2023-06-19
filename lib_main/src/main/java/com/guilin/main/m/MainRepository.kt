package com.guilin.main.m

import com.guilin.base.mvvm.m.BaseRepository
import com.guilin.common.net.NetRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 3:58 PM
 */
class MainRepository @Inject constructor(): BaseRepository() {

    /**
     * 模拟请求或读取数据库
     */
    suspend fun getString(): Flow<String> {
        val flowRequest = flowRequest<String> {
            emit("嘿嘿嘿")
        }
        return flowRequest
    }

    /**
     * 模拟使用网络请求接口
     * 需要写成挂起函数
     */
    suspend fun mockRequest() = flowRequest<String> {
        // 发起请求
        // 并行请求可以使用 async await
        // 例如
        //  创建一个新的协程进行请求
        // 发起请求
        // 并行请求可以使用 async await
        // 例如
        //  创建一个新的协程进行请求
//        val deferredRealtime = async {
//            SendRequest.getRealtimeWeather(lng, lat)
//        }
//        // 创建一个新的协程进行请求
//        val deferredDaily = async {
//            SendRequest.getDailyWeather(lng, lat)
//        }
//        // 对两个请求获取结果
//        val realtimeResponse = deferredRealtime.await()
//        val dailyResponse = deferredDaily.await()
        //这两个协程是并行的 不是串行
        val testBean = NetRequest.homeService.getTestData("mock")
        emit(testBean.msgTest)

    }


}

