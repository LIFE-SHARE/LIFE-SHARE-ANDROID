package com.example.lifeshare_android.view.activity

import android.content.Intent
import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityLoginBinding
import com.example.lifeshare_android.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    // TODO : 집주인 계정: alskt0419, 1234 || 손님 계정: gotjd0419, 1234

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
                if(isEmpty()) {
                    simpleToast(R.string.empty_message)
                    return@Observer
                }
                viewModel.login()
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