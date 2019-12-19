package com.example.lifeshare_android.view.dialog

import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.BaseDialog
import com.example.lifeshare_android.databinding.DialogExitBinding
import com.example.lifeshare_android.view.activity.LoginActivity
import com.example.lifeshare_android.viewmodel.LogoutViewModel
import com.example.lifeshare_android.widget.extension.startActivityWithFinish

class LogoutDialog : BaseDialog<DialogExitBinding, LogoutViewModel>() {

    private val TAG: String? = LogoutDialog::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.dialog_exit
    }

    override fun getViewModel(): Class<LogoutViewModel> {
        return LogoutViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            backEvent.observe(this@LogoutDialog, Observer {
                dismiss()
            })

            openLogin.observe(this@LogoutDialog, Observer {
                this@LogoutDialog.startActivityWithFinish(LoginActivity::class.java)
            })
        }
    }

    fun show(fragmentManager: FragmentManager?) {
        super.show(fragmentManager!!, TAG)
    }
}