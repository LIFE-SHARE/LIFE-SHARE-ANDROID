package com.example.lifeshare_android.widget.recyclerview.viewholder

import com.example.lifeshare_android.base.BaseViewHolder
import com.example.lifeshare_android.databinding.ItemHouseBinding
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.widget.recyclerview.navigator.house.HouseAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.navigator.house.HouseItemNavigator
import com.example.lifeshare_android.widget.recyclerview.viewmodel.HouseItemViewModel

class HouseViewHolder(val binding: ItemHouseBinding) : BaseViewHolder<HouseAdapterNavigator>(binding.root),
    HouseItemNavigator {

    private val viewModel = HouseItemViewModel()

    private lateinit var house: House

    override fun openHouse() {
        getNavigator().openHouse(house.id)
    }

    fun bind(data: House) {
        viewModel.bind(data)
        house = data
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }
}