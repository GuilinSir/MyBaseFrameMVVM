package com.guilin.main.v

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.ktx.toast
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils
import com.guilin.common.bean.TestBean
import com.guilin.common.constant.RouteKey
import com.guilin.common.constant.RouteUrl
import com.guilin.common.ui.BaseActivity
import com.guilin.main.vm.MainViewModel
import com.guilin.main.databinding.MainActivityMainBinding
import org.greenrobot.eventbus.Subscribe

@EventBusRegister
@Route(path = RouteUrl.MainActivity)
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding(): MainActivityMainBinding {
        return MainActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewModel.msgTest.observe(this) {
            mBinding.tvText.text = it
            toast(it)
        }
        mBinding.mBtn.setOnClickListener { mViewModel.getTestString() }
        mBinding.mIntentBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouteUrl.MainActivity2)
                .withString(RouteKey.KEY_NAME, "ARouter")
                .navigation()
        }
        mBinding.mEventBusBtn.setOnClickListener{
            EventBusUtils.postEvent(TestBean("EventBus"))
        }

    }

    @Subscribe
    fun onEvent(event: TestBean) {
        toast(event.msgTest)
    }
}