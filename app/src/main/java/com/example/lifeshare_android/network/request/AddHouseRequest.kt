package com.example.lifeshare_android.network.request

class AddHouseRequest(var name: String,
                      var address: String,
                      var genderLimit: String,
                      var ageLimit: Int,
                      var maxMember: Int,
                      var contractperiod: String,
                      var information: String,
                      var image: String) {
    constructor() : this("", "", "", 0, 0,"", "", "")
}