package com.example.lifeshare_android.network.request

class AddHouseRequest(var name: String,
                      var address: String,
                      var genderLimit: String,
                      var ageLimit: String,
                      var contractperiod: String,
                      var maxMember: String,
                      var information: String,
                      var image: String) {
    constructor() : this("", "", "", "", "","", "", "")
}