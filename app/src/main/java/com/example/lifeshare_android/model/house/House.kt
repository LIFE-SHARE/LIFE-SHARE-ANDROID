package com.example.lifeshare_android.model.house

data class House(var id: Int,
                 var userId: String,
                 var name: String,
                 var address: String,
                 var genderLimit: String,
                 var ageLimit: Int,
                 var maxMember: Int,
                 var contractperiod: String,
                 var information: String,
                 var imageData: String,
                 var join_date: String)