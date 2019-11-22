package com.example.lifeshare_android.model.apply

data class Apply(var id: Int,
                 var userName: String,
                 var houseId: Int,
                 var message: String,
                 var userAge: String,
                 var gender: Int,   // TODO : 0 남자, 1 여자
                 var join_date: String)