package com.example.lifeshare_android.network.request

class AddHouseRequest(var name: String,
                      var address: String,
                      var genderLimit: String,
                      var ageLimit: Int,
                      var contractperiod: String,
                      var maxMember: Int,
                      var information: String,
                      var image: String) {
    constructor() : this("", "", "", 0, "",0, "", "")
}