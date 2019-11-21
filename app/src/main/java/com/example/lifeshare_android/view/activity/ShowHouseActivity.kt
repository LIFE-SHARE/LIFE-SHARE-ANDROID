package com.example.lifeshare_android.view.activity

import android.os.Bundle

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityShowHouseBinding
import com.example.lifeshare_android.viewmodel.ShowHouseViewModel

class ShowHouseActivity : BaseActivity<ActivityShowHouseBinding, ShowHouseViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_show_house
    }

    override fun getViewModel(): Class<ShowHouseViewModel> {
        return ShowHouseViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
        viewModel.setUp()
    }

    private fun setUp() {
        when {
            intent!!.hasExtra("houseId") -> viewModel.houseId.value = intent!!.getIntExtra("houseId", 0)
            else -> {
                simpleToast("houseId Null")
            }
        }
    }
}
