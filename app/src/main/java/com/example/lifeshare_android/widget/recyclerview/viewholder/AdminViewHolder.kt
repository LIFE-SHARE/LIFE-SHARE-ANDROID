package com.example.lifeshare_android.widget.recyclerview.viewholder

import com.example.lifeshare_android.base.BaseViewHolder
import com.example.lifeshare_android.databinding.ItemAdminMainBinding
import com.example.lifeshare_android.model.admin.ApplyList
import com.example.lifeshare_android.widget.recyclerview.navigator.AdminAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.navigator.AdminItemNavigator
import com.example.lifeshare_android.widget.recyclerview.viewmodel.AdminItemViewModel

class AdminViewHolder(val binding: ItemAdminMainBinding) : BaseViewHolder<AdminAdapterNavigator>(binding.root), AdminItemNavigator {

    private val viewModel = AdminItemViewModel()

    private lateinit var applyList: ApplyList

//    override fun clickItemEvent() {
//        getNavigator().intentItem()
//    }

    fun bind(data: ApplyList) {
        viewModel.bind(data)
        applyList = data
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }
}