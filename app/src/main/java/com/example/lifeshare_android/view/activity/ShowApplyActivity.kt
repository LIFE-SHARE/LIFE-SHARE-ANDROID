package com.example.lifeshare_android.view.activity

import android.os.Bundle

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityShowApplyBinding
import com.example.lifeshare_android.viewmodel.ShowApplyViewModel
import com.example.lifeshare_android.widget.extension.shortToast

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
                    viewModel.accept(it!!)
                    this@ShowApplyActivity.shortToast("수락 되었습니다")
                    finish()
                })

                onClickRefusalEvent.observe(this@ShowApplyActivity, Observer {
                    viewModel.refusal(it!!)
                    this@ShowApplyActivity.shortToast("거절 되었습니다")
                    finish()
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
                this@ShowApplyActivity.shortToast("houseId : null")
            }
        }
    }
}