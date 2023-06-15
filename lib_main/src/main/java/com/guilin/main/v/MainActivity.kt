package com.guilin.main.v

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.guilin.base.utils.EventBusRegister
import com.guilin.base.utils.EventBusUtils
import com.guilin.base.utils.toast
import com.guilin.common.bean.TestBean
import com.guilin.common.constant.RouteKey
import com.guilin.common.constant.RouteUrl
import com.guilin.common.ui.BaseActivity
import com.guilin.main.vm.MainViewModel
import com.guilin.main.databinding.MainActivityMainBinding
import com.permissionx.guolindev.PermissionX
import org.greenrobot.eventbus.Subscribe
import java.util.jar.Manifest

@EventBusRegister
@Route(path = RouteUrl.MainActivity)
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>() {

//    override fun initViewBinding(): MainActivityMainBinding {
//        return MainActivityMainBinding.inflate(layoutInflater)
//    }

    override fun MainActivityMainBinding.initView() {

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
        (event.msgTest)
    }



    override fun initLiveDataObserve() {
        mViewModel.msgTest.observe(this) {
            mBinding.tvText.text = it
            toast(it)
        }
        PermissionX.init(this)
            .permissions(android.Manifest.permission.READ_PHONE_STATE)
            .request { allGranted, grantedList, deniedList ->
                if(allGranted){
                    toast("权限全部授予")
                }else{
                    toast("您拒绝了权限")
                }
            }
    }

    override fun initRequestData() {
        TODO("Not yet implemented")
    }
}