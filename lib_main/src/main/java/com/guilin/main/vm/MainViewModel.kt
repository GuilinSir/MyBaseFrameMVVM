package com.guilin.main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guilin.base.mvvm.vm.BaseViewModel
import com.guilin.main.m.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @description:
 * @author:  guilin
 * @email:   308139995@qq.com
 * @date :   2023/6/12 3:57 PM
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val mRepository: MainRepository) : BaseViewModel() {
    val msg: MutableLiveData<String> = MutableLiveData<String>()
    val msgTest: MutableLiveData<String> = MutableLiveData<String>()



    fun getString() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.getString().collectLatest {
                msg.postValue(it)
            }
        }
    }

    fun getTestString() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.mockRequest()
                .onStart {
                    // 获取数据之前
                    // 可以做loading图之类的
                }
                .catch {
                    // 处理异常 获取数据产生的异常
                }
                .onCompletion {
                    // 获取数据完成时
                }
                .collectLatest {
                    // 拿到想要的数据
                    msgTest.postValue(it)
                    Log.d("MainViewModel", "getTestString: $it")
                }
            // onStart()  catch()  onCompletion() 都是可选的 是flow的操作符
            // 例如  mRepository.getString().collectLatest { // 拿到想要的结果 }

        }
    }
}