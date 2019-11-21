package com.example.lifeshare_android.model.room

data class Room(var id: Int,
                var house_id: Int,
                var peopleCnt: Int,
                var deposit: Int,
                var monthly: Int,
                var imageData: String)