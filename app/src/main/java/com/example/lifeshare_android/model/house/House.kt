package com.example.lifeshare_android.model.house

import com.example.lifeshare_android.model.room.Room

data class House(var id: Int,
                 var userId: String,
                 var name: String,
                 var address: String,
                 var genderLimit: Int,
                 var ageLimit: Int,
                 var contractperiod: String,
                 var information: String,
                 var maxMember: Int,
                 var imageData: String,
                 var join_date: String,
                 var room_data: List<Room>)