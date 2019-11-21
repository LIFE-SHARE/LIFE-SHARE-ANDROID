package com.example.lifeshare_android.view.activity

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivitySearchBinding
import com.example.lifeshare_android.viewmodel.SearchViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun getViewModel(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }
}