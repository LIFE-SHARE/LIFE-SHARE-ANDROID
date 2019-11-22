package com.example.lifeshare_android.widget.recyclerview.viewholder

import com.example.lifeshare_android.base.BaseViewHolder
import com.example.lifeshare_android.databinding.ItemApplyBinding
import com.example.lifeshare_android.model.apply.Apply
import com.example.lifeshare_android.widget.recyclerview.navigator.ApplyAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.navigator.ApplyItemNavigator
import com.example.lifeshare_android.widget.recyclerview.viewmodel.ApplyItemViewModel

class ApplyViewHolder(val binding: ItemApplyBinding) : BaseViewHolder<ApplyAdapterNavigator>(binding.root), ApplyItemNavigator {

    private val viewModel = ApplyItemViewModel()

    private lateinit var apply: Apply

    override fun onClickAcceptBtn() {
        getNavigator().onClickAccept(apply.id)
    }

    override fun onClickRefusalBtn() {
        getNavigator().onClickRefusal(apply.id)
    }

    fun bind(data: Apply) {
        viewModel.bind(data)
        apply = data
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }
}