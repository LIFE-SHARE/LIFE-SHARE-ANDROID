package com.example.lifeshare_android.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityShowApplyBinding
import com.example.lifeshare_android.viewmodel.ShowApplyViewModel

class ShowApplyActivity : BaseActivity<ActivityShowApplyBinding, ShowApplyViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_show_apply
    }

    override fun getViewModel(): Class<ShowApplyViewModel> {
        return ShowApplyViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            with(applyAdapter) {

                onClickAcceptEvent.observe(this@ShowApplyActivity, Observer {
                    simpleToast("11111")
                })

                onClickRefusalEvent.observe(this@ShowApplyActivity, Observer {
                    simpleToast("22222")
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
        viewModel.setUp()
    }

    private fun setUp() {
        when {
            intent!!.hasExtra("houseId") -> viewModel.houseId.value = intent!!.getStringExtra("houseId").toInt()
            else -> {
                simpleToast("houseId : null")
            }
        }
    }
}