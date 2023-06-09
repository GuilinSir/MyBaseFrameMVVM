package com.guilin.main.v

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.guilin.common.constant.RouteKey
import com.guilin.common.constant.RouteUrl
import com.guilin.common.ui.BaseActivity
import com.guilin.main.databinding.MainActivityMain2Binding
import com.guilin.main.vm.MainViewModel

@Route(path = RouteUrl.MainActivity2)
class MainActivity2 :
    BaseActivity<MainActivityMain2Binding>() {
    @Autowired(name = RouteKey.KEY_NAME)
    lateinit var name: String

//    override fun initViewBinding(): MainActivityMain2Binding {
//        return MainActivityMain2Binding.inflate(layoutInflater)
//    }

    override fun MainActivityMain2Binding.initView() {
    }

    override fun initLiveDataObserve() {
    }

    override fun initRequestData() {
    }
}