package com.guilin.main.v

import android.widget.Toast
import com.guilin.common.ui.BaseActivity
import com.guilin.main.vm.MainViewModel
import com.guilin.main.databinding.MainActivityMainBinding

class MainActivity : BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding(): MainActivityMainBinding {
        return MainActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewModel.msg.observe(this) {
            mBinding.tvText.text = it
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        mBinding.mBtn.setOnClickListener { mViewModel.getString() }
    }
}