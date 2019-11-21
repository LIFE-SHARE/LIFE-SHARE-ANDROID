package com.example.lifeshare_android.model.house

import com.example.lifeshare_android.model.room.Room

data class House(var id: Int,
                 var userId: String,
                 var name: String,
                 var address: String,
                 var genderLimit: Int,
                 var ageLimit: Int,
                 var contractperiod: Int,
                 var information: String,
                 var maxDeposit: Int,
                 var maxMonthly: Int,
                 var imageData: String,
                 var rooms: List<Room>)