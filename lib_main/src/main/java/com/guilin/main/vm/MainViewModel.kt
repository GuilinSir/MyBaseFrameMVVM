package com.guilin.main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guilin.base.mvvm.vm.BaseViewModel
import com.guilin.main.m.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 3:57 PM
 */
class MainViewModel : BaseViewModel<MainRepository>() {
    val msg: MutableLiveData<String> = MutableLiveData<String>()

    override fun initRepository(): MainRepository {
        return MainRepository()
    }

    fun getString() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.getString().collectLatest {
                msg.postValue(it)
            }
        }
    }
}