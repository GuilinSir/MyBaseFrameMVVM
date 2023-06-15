package com.guilin.base.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilin.base.mvvm.m.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * @description:ViewModel 基类
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 2:15 PM
 */
abstract class BaseViewModel : ViewModel() {

    val isLoding = MutableLiveData(false)

    // 请求异常
    val requestError = MutableLiveData<Throwable?>()

}