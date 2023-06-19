package com.guilin.main.v

import androidx.activity.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.Subscribe
import java.util.jar.Manifest

@EventBusRegister
@Route(path = RouteUrl.MainActivity)
@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityMainBinding>() {

    /**
     * 通过 viewModels() + Hilt 获取 ViewModel 实例
     */
    private val mViewModel by viewModels<MainViewModel>()

    override fun MainActivityMainBinding.initView() {

        mBinding.mBtn.setOnClickListener {
            mViewModel.getString()
            mViewModel.getTestString()

        }
        mBinding.mIntentBtn.setOnClickListener {
            ARouter.getInstance()
                .build(RouteUrl.MainActivity2)
                .withString(RouteKey.KEY_NAME, "ARouter")
                .navigation()
        }
        mBinding.mEventBusBtn.setOnClickListener {
            EventBusUtils.postEvent(TestBean("EventBus"))
        }

    }

    @Subscribe
    fun onEvent(event: TestBean) {
        toast(event.msgTest)
    }

    override fun initLiveDataObserve() {
        mViewModel.msg.observe(this) {
            mBinding.tvText.text = it
            toast(it)
        }
        PermissionX.init(this)
            .permissions(android.Manifest.permission.READ_PHONE_STATE)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    toast("权限全部授予")
                } else {
                    toast("您拒绝了权限")
                }
            }
    }

    override fun initRequestData() {
    }
}