package com.example.lifeshare_android.view.activity

import android.content.Intent

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

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

            backEvent.observe(this@ShowHouseActivity, Observer {
                finish()
            })

            addRoomEvent.observe(this@ShowHouseActivity, Observer {
                startActivity(Intent(this@ShowHouseActivity, AddRoomActivity::class.java)
                    .putExtra("houseId", it!!.toString()))
            })

            applyCheckEvent.observe(this@ShowHouseActivity, Observer {
                startActivity(Intent(this@ShowHouseActivity, ShowApplyActivity::class.java)
                    .putExtra("houseId", it!!.toString()))
            })

            with(roomAdapter) {

                openRoom.observe(this@ShowHouseActivity, Observer {
                    simpleToast(it!!) // TODO : onClickRoomItem <추후 업데이트 할 내용> : 방 정보 확인하기 정도 예상
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
            intent!!.hasExtra("houseId") -> viewModel.houseId.value = intent!!.getIntExtra("houseId", 0)
            else -> {
                simpleToast("houseId Null")
            }
        }
        binding.roomRecyclerview.layoutManager = GridLayoutManager(this, 2)
    }
}
