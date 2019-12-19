package com.example.lifeshare_android.view.activity

import android.content.Intent

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityLoginBinding
import com.example.lifeshare_android.viewmodel.LoginViewModel
import com.example.lifeshare_android.widget.extension.shortToast

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    // TODO : 집주인 계정: test1, 1234 || 손님 계정: test2, 1234

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            loginEvent.observe(this@LoginActivity, Observer {
                when {
                    isEmpty() -> {
                        this@LoginActivity.shortToast(R.string.empty_message)
                        return@Observer
                    }
                    else -> viewModel.login()
                }
            })

            onSuccessEvent.observe(this@LoginActivity, Observer {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java)
                    .putExtra("userName", it!!))
                finish()
            })
        }
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.id == null || viewModel.request.pw == null
    }
}