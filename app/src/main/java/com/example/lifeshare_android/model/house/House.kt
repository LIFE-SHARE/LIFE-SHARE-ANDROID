package com.example.lifeshare_android.model.house

data class House(var id: String,
                 var userId: String,
                 var name: String,
                 var address: String,
                 var genderLimit: Int,
                 var ageLimit: Int,
                 var contractperiod: Int,
                 var information: String,
                 var maxDeposit: Int,
                 var maxMonthly: Int,
                 var imageData: String)